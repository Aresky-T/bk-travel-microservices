package com.aresky.authservice.service.auth;

import com.aresky.authservice.kafka.KafkaMessageType;
import com.aresky.authservice.kafka.KafkaSenderEvent;
import com.aresky.authservice.kafka.KafkaTopic;
import io.r2dbc.spi.Row;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aresky.authservice.dto.LoginForm;
import com.aresky.authservice.dto.LoginResponse;
import com.aresky.authservice.dto.SignupForm;
import com.aresky.authservice.entity.Auth;
import com.aresky.authservice.exception.AuthException;
import com.aresky.authservice.jwt.JwtUtils;
import com.aresky.authservice.repository.AuthRepository;
import com.aresky.authservice.constants.ExceptionNotification;
import com.aresky.authservice.service.account.IAccountGrpcService;
import com.aresky.authservice.service.thymeleaf.IThymeleafService;

import grpc.account.v2.dto.response.AccountResponse;
import reactor.core.publisher.Mono;

@Service
public class AuthServiceImp implements IAuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private IAccountGrpcService accountGrpcService;

    @Autowired
    private IThymeleafService thymeleafService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    public DatabaseClient databaseClient;

    @Autowired
    public KafkaSenderEvent kafkaSenderEvent;

    @Value("${spring.mail.username}")
    private String email;

    @Transactional
    @Override
    public Mono<LoginResponse> handleLogin(LoginForm form) {
        String username = form.getUsername();
        String password = form.getPassword();

        return accountGrpcService.checkAccountByUsername(form.getUsername())
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.ACCOUNT_NOT_EXISTS)))
                .flatMap(existsAccount -> accountGrpcService.getAccountByUsernameAndPassword(username, password))
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.INVALID_PASSWORD)))
                .flatMap(account -> {
                    Map<String, Object> props = new HashMap<>();
                    props.put("sub", username);
                    props.put("email", account.getEmail());
                    props.put("role", account.getRole());

                    String token = jwtUtils.generateToken(props);
                    LoginResponse loginResponse = LoginResponse
                            .builder()
                            .id(account.getId())
                            .email(account.getEmail())
                            .username(account.getUsername())
                            .type(jwtUtils.getPrefix())
                            .token(token)
                            .role(account.getRole())
                            .status(account.getStatus())
                            .build();

                    Integer accountId = account.getId();
                    return existsAuthByAccountId(accountId)
                            .flatMap(existsAuth -> existsAuth
                                    ? findAuthByAccountId(accountId)
                                    .flatMap(auth -> {
                                        auth.setAccessToken(token);
                                        return authRepository.save(auth);
                                    })
                                    : createAuth(accountId, token)
                            )
                            .then()
                            .thenReturn(loginResponse);
                });
    }

    @Override
    public Mono<Boolean> validateAccessToken(String token) {
        return Mono.just(jwtUtils.isValidToken(token))
                .flatMap(isValid -> {
                    if (!isValid)
                        return Mono.just(false);

                    String email = jwtUtils.getClaimsFromToken(token).get("email", String.class);
                    System.out.println("Subject: " + jwtUtils.getClaimsFromToken(token).getSubject());
                    System.out.println("Email: " + email);

                    return Mono.justOrEmpty(email)
                            .flatMap(accountGrpcService::getAccountByEmail)
                            .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.ACCOUNT_NOT_EXISTS)))
                            .map(AccountResponse::getId)
                            .flatMap(authRepository::findByAccountId)
                            .map(auth -> auth.getAccessToken().equals(token));
                });
    }

    @Transactional
    @Override
    public Mono<String> handleSignup(SignupForm form) {
        return accountGrpcService.createAccount(form.getUsername(), form.getEmail(), form.getPassword())
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.ACCOUNT_ALREADY_EXISTS)))
                .thenReturn("success");
    }

    @Override
    public Mono<String> handleForgotPassword(String email) {
        return accountGrpcService.checkAccountByEmail(email)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.ACCOUNT_NOT_EXISTS)))
                .then(accountGrpcService.resetPassword(email))
                .flatMap(newPassword -> {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                    String now = dateFormat.format(new Date(System.currentTimeMillis()));

                    // Add variable to context thymeleaf
                    Map<String, Object> variables = new HashMap<>();
                    variables.put("requiredResetPasswordTime", now);
                    variables.put("newPasswordRandom", newPassword);
                    // Send email
                    final String FORGOT_PASSWORD_TEMPLATE_FILE_NAME = "forgot-password-template";
                    String template = thymeleafService.createContent(FORGOT_PASSWORD_TEMPLATE_FILE_NAME, variables);

                    return sendMailWithHtmlTemplate(
                            email,
                            "BK TRAVEL - Yêu cầu đặt lại mật khẩu",
                            template)
                            .thenReturn("success");
                });
    }

    public Mono<Auth> findAuthByAccountId(int accountId) {
        String query = "SELECT * FROM auth WHERE account_id = :accountId";
        return databaseClient.sql(query).bind("accountId", accountId)
                .map(((row, rowMetadata) -> mapRowToAuth(row)))
                .one();
    }

    public Mono<Auth> createAuth(Integer accountId, String accessToken){
        return authRepository.save(new Auth(accountId, accessToken))
                .map(auth -> {

                    KafkaMessageType.NotificationRequest notification
                            = KafkaMessageType.NotificationRequest.builder()
                            .userId(accountId);

                    kafkaSenderEvent.sendMessage(
                            KafkaTopic.FIRST_TIME_LOGIN,
                            KafkaSenderEvent.KEY,
                            notification
                    );

                    return auth;
                });
    }

    private Mono<Boolean> existsAuthByAccountId(Integer accountId){
        return authRepository.existsByAccountId(accountId);
    }

    private Auth mapRowToAuth(Row row) {
        Auth auth = new Auth();
        auth.setId(row.get("id", Integer.class));
        auth.setAccountId(row.get("account_id", Integer.class));
        auth.setAccessToken(row.get("access_token", String.class));
        return auth;
    }

    private Mono<Void> sendMailWithHtmlTemplate(String recipient, String subject, String body) {
        return Mono.fromRunnable(() -> {
            try {
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message,
                        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
                helper.setTo(recipient);
                helper.setFrom(email, "Bk Travel");
                helper.setSubject(subject);
                helper.setText(body, true);
                javaMailSender.send(message);
            } catch (MessagingException | UnsupportedEncodingException ex) {
                ex.printStackTrace(System.err);
            }
        });
    }
}

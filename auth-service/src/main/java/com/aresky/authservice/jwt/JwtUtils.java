package com.aresky.authservice.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.aresky.authservice.constants.ExceptionNotification;
import com.aresky.authservice.exception.AuthException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;

@Slf4j
@Component
public class JwtUtils {

    @Autowired
    private Environment environment;

    private String header;
    private String prefix;
    private String secret;
    private Long expiration;
    private Key key;

    @PostConstruct
    public void initEnvVariables() {
        this.header = String.valueOf(environment.getProperty(JwtConfig.JWT_HEADER));
        this.prefix = String.valueOf(environment.getProperty(JwtConfig.JWT_PREFIX));
        this.expiration = Long.valueOf(Objects.requireNonNull(environment.getProperty(JwtConfig.JWT_EXPIRATION)));
        this.secret = String.valueOf(environment.getProperty(JwtConfig.JWT_SECRET));
        this.key = getSigningKey(this.secret);
    }

    public String generateToken(String subject) {
        return getBuilder().setSubject(subject).compact();
    }

    public String generateToken(Claims claims) {
        return getBuilder().setClaims(claims).compact();
    }

    public String generateToken(Map<String, Object> props) {
        return getBuilder().setClaims(props).compact();
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
    }

    public String getSubjectFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if (claims == null || claims.isEmpty()) {
            return null;
        }

        return claims.getSubject();
    }

    public Boolean isValidToken(String token) {
        try {
            return getSubjectFromToken(token) != null && !isExpiredToken(token);
        } catch (SignatureException | MalformedJwtException e) {
            log.error("Invalid token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token: {}", e.getMessage());
            throw new AuthException(ExceptionNotification.SESSION_HAS_EXPIRED);
        } catch (IllegalArgumentException e) {
            log.error("JWT Claims is empty: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } finally {
            log.info("validate success");
        }
        return false;
    }

    public Boolean isExpiredToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

    private Key getSigningKey(String secret) {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(secretBytes);
    }

    private JwtBuilder getBuilder() {
        Date now = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(now.getTime() + this.expiration);
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS512);
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
}

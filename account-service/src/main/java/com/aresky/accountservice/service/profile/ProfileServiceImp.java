package com.aresky.accountservice.service.profile;

import java.lang.reflect.Field;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.aresky.accountservice.dto.request.ProfileUpdateForm;
import com.aresky.accountservice.dto.response.ProfileResponse;
import com.aresky.accountservice.exception.AccountException;
import com.aresky.accountservice.exception.ExceptionNotification;
import com.aresky.accountservice.model.EGender;
import com.aresky.accountservice.model.Profile;
import com.aresky.accountservice.repository.AccountRepository;
import com.aresky.accountservice.repository.ProfileRepository;

import reactor.core.publisher.Mono;

@Service
public class ProfileServiceImp implements IProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    @Override
    public Mono<Boolean> addNewProfile(int accountId) {
        return profileRepository.existsByAccountId(accountId)
                .map(isExists -> {
                    if (!isExists) {
                        profileRepository.save(new Profile(accountId));
                        return true;
                    }
                    return false;
                });
    }

    @Override
    public Mono<ProfileResponse> findByAccountId(int accountId) {
        return accountRepository.findById(accountId)
                .flatMap(account -> profileRepository.findByAccountId(accountId)
                        .map(profile -> ProfileResponse.toDTO(profile, account))
                        .switchIfEmpty(profileRepository
                                .save(new Profile(accountId))
                                .map(profile -> ProfileResponse.toDTO(profile, account))))
                .switchIfEmpty(Mono.empty());
    }

    @Transactional
    @Override
    public Mono<ProfileResponse> updateProfile(ProfileUpdateForm form, int accountId) {
        return accountRepository.findById(accountId)
                .filter(Objects::nonNull)
                .flatMap(account -> profileRepository.findByAccountId(accountId)
                        .flatMap(profile -> {
                            profile.setAddress(form.getAddress());
                            profile.setFullName(form.getFullName());
                            profile.setPhone(form.getPhone());
                            profile.setGender(form.getGender());

                            Date dateOfBirth = form.getDateOfBirth();
                            profile.setDateOfBirth(dateOfBirth.toInstant().atZone(ZoneId.systemDefault()));

                            return profileRepository.save(profile)
                                    .map(updatedProfile -> ProfileResponse.toDTO(updatedProfile, account));
                        }))
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<ProfileResponse> updateProfileByFields(int accountId, Map<String, Object> fields) {
        return accountRepository.findById(accountId)
                .flatMap(account -> profileRepository.findByAccountId(accountId)
                        .flatMap(profile -> {
                            fields.forEach((key, value) -> {
                                Field field = ReflectionUtils.findField(Profile.class, key);
                                if (Objects.isNull(field)) {
                                    throw new AccountException("Field not found: " + "[" + key + "]");
                                }

                                if (key.equalsIgnoreCase("gender")) {
                                    value = convertToERole(String.valueOf(value));
                                }

                                ReflectionUtils.makeAccessible(field);
                                ReflectionUtils.setField(field, profile, value);
                            });

                            return profileRepository.save(profile)
                                    .map(updatedProfile -> ProfileResponse.toDTO(updatedProfile, account));
                        }))
                .switchIfEmpty(Mono.error(new AccountException(ExceptionNotification.ACCOUNT_NOT_EXISTS)));
    }

    @Override
    public Mono<ProfileResponse> updateAvatar(int accountId, String newAvatar) {
        return accountRepository.findById(accountId)
                .flatMap(account -> profileRepository.findByAccountId(accountId)
                        .flatMap(profile -> {
                            profile.setAvatarUrl(newAvatar);
                            return profileRepository.save(profile)
                                    .map(updatedProfile -> ProfileResponse.toDTO(updatedProfile, account));
                        }))
                .switchIfEmpty(Mono.error(new AccountException("Account does not exist!")));
    }

    private EGender convertToERole(String gender) {
        switch (gender) {
            case "MALE":
                return EGender.MALE;
            case "FEMALE":
                return EGender.FEMALE;
            case "OTHER":
                return EGender.OTHER;
            default:
                throw new AccountException("Giới tính không hợp lệ!");
        }
    }
}

package com.aresky.accountservice.service.profile;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aresky.accountservice.dto.request.ProfileUpdateForm;
import com.aresky.accountservice.dto.response.ProfileResponse;
import com.aresky.accountservice.exception.AccountException;
import com.aresky.accountservice.exception.ExceptionNotification;
import com.aresky.accountservice.model.EGender;
import com.aresky.accountservice.model.Profile;
import com.aresky.accountservice.repository.AccountRepository;
import com.aresky.accountservice.repository.ProfileRepository;
import com.aresky.accountservice.utils.FieldUtils;

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
    public Mono<Profile> findByAccountId(int accountId) {
        return accountRepository.existsById(accountId)
                .flatMap(existsAccount -> {
                    if(!existsAccount){
                        return Mono.empty();
                    }

                    return  profileRepository.findByAccountId(accountId)
                            .switchIfEmpty(profileRepository.save(new Profile(accountId)));
                });
    }

    @Override
    public Mono<Profile> findByAccountEmail(String email) {
        return accountRepository.findByEmail(email)
                .switchIfEmpty(Mono.empty())
                .flatMap(account -> profileRepository.findByAccountId(account.getId())
                        .switchIfEmpty(profileRepository.save(new Profile(account.getId()))));
    }

    @Override
    public Mono<ProfileResponse> findProfileResponseByAccountId(int accountId) {
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
                            profile.setDateOfBirth(new java.sql.Date(dateOfBirth.getTime()).toLocalDate());

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
                            for (Entry<String, Object> entry : fields.entrySet()) {
                                String fieldName = entry.getKey();
                                Object fieldValue = entry.getValue();

                                Field field = FieldUtils.findField(profile, fieldName);
                                FieldUtils.setFieldValue(profile, field, fieldValue);
                            }

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

    @SuppressWarnings("unused")
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

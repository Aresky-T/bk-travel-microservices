package com.aresky.accountservice.service.profile;

import java.util.Map;

import com.aresky.accountservice.dto.request.ProfileUpdateForm;
import com.aresky.accountservice.dto.response.ProfileResponse;

import reactor.core.publisher.Mono;

public interface IProfileService {
    Mono<Boolean> addNewProfile(int accountId);

    Mono<ProfileResponse> findByAccountId(int accountId);

    Mono<ProfileResponse> updateProfile(ProfileUpdateForm form, int accountId);

    Mono<ProfileResponse> updateProfileByFields(int accountId, Map<String, Object> fields);

    Mono<ProfileResponse> updateAvatar(int accountId, String newAvatar);
}

package com.aresky.accountservice.service.profile;

import java.util.Map;

import com.aresky.accountservice.dto.request.ProfileUpdateForm;
import com.aresky.accountservice.dto.response.ProfileResponse;

import com.aresky.accountservice.model.Profile;
import reactor.core.publisher.Mono;

public interface IProfileService {
    Mono<Boolean> addNewProfile(int accountId);

    Mono<Profile> findByAccountId(int accountId);

    Mono<Profile> findByAccountEmail(String email);

    Mono<ProfileResponse> findProfileResponseByAccountId(int accountId);

    Mono<ProfileResponse> updateProfile(ProfileUpdateForm form, int accountId);

    Mono<ProfileResponse> updateProfileByFields(int accountId, Map<String, Object> fields);

    Mono<ProfileResponse> updateAvatar(int accountId, String newAvatar);
}

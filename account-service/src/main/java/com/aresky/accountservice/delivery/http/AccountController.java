package com.aresky.accountservice.delivery.http;

import com.aresky.accountservice.dto.request.ProfileUpdateForm;
import com.aresky.accountservice.dto.request.SignupForm;
import com.aresky.accountservice.dto.request.UpdatePasswordForm;
import com.aresky.accountservice.dto.response.AccountResponse;
import com.aresky.accountservice.dto.response.ProfileResponse;
import com.aresky.accountservice.model.ERole;
import com.aresky.accountservice.service.account.IAccountService;
import com.aresky.accountservice.service.profile.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IProfileService profileService;

    // Account APIs

    @GetMapping
    public Mono<ResponseEntity<Page<AccountResponse>>> getAllAccounts(Pageable pageable) {
        return accountService.findAll(pageable)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @GetMapping("/find/id")
    public Mono<ResponseEntity<AccountResponse>> getAccountById(@RequestParam Integer id) {
        return accountService.findById(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/find/username")
    public Mono<ResponseEntity<AccountResponse>> getAccountByUsername(@RequestParam String username) {
        return accountService.findByUsername(username)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/find/email")
    public Mono<ResponseEntity<AccountResponse>> getAccountByEmail(@RequestParam String email) {
        return accountService.findByEmail(email)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/find/username-and-password")
    public Mono<ResponseEntity<AccountResponse>> getAccountByUsernameAndPassword(
            @RequestParam String username,
            @RequestParam String password) {
        return accountService.findByUsernameOrPassword(username, password)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/validate")
    public Mono<ResponseEntity<?>> validateAccount(@RequestParam(name = "id") int accountId) {
        return accountService.isExistsById(accountId).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<?>> onSignUpAccount(@RequestBody SignupForm form) {
        return accountService.handleSignup(form).thenReturn(ResponseEntity.ok("success"));
    }

    @PatchMapping("/update-password")
    public Mono<ResponseEntity<?>> onUpdatePassword(
            @RequestParam String username,
            @RequestBody UpdatePasswordForm form) {
        return accountService.handleUpdatePassword(form, username)
                .thenReturn(ResponseEntity.ok("success"));
    }

    @PatchMapping("/activation-status/lock")
    public Mono<ResponseEntity<?>> onLockAccount(@RequestParam Integer id) {
        return accountService.lock(id).thenReturn(ResponseEntity.ok("success"));
    }

    @PatchMapping("/activation-status/activate")
    public Mono<ResponseEntity<?>> onActivateAccount(@RequestParam Integer id) {
        return accountService.activate(id).thenReturn(ResponseEntity.ok("success"));
    }

    @PatchMapping("/role")
    public Mono<ResponseEntity<?>> onUpdateRole(@RequestParam Integer id, ERole role) {
        return accountService.updateRole(id, role).thenReturn(ResponseEntity.ok("success"));
    }

    @DeleteMapping
    public Mono<ResponseEntity<?>> onDeleteAccount(@RequestParam Integer id) {
        return accountService.delete(id).thenReturn(ResponseEntity.ok("success"));
    }

    // Profile APIs

    @GetMapping("/profile")
    Mono<ResponseEntity<ProfileResponse>> getProfileByAccountId(@RequestParam Integer accountId) {
        return profileService.findProfileResponseByAccountId(accountId).map(ResponseEntity::ok);
    }

    @PostMapping("/profile")
    Mono<ResponseEntity<?>> onCreateProfile(@RequestParam Integer accountId) {
        return profileService.addNewProfile(accountId).thenReturn(ResponseEntity.ok("success"));
    }

    @PatchMapping("/profile/avatar")
    Mono<ResponseEntity<?>> onUpdateAvatar(
            @RequestParam Integer accountId,
            @RequestParam String newAvatar) {
        return profileService.updateAvatar(accountId, newAvatar).map(ResponseEntity::ok);
    }

    @PutMapping("/profile")
    Mono<ResponseEntity<?>> onUpdateProfile(
            @RequestParam Integer accountId,
            @RequestBody ProfileUpdateForm form) {
        return profileService.updateProfile(form, accountId).map(ResponseEntity::ok);
    }

    @PatchMapping("/profile")
    Mono<ResponseEntity<?>> onUpdateProfile(
            @RequestParam Integer accountId,
            @RequestBody Map<String, Object> fields) {
        return profileService.updateProfileByFields(accountId, fields).map(ResponseEntity::ok);
    }
}

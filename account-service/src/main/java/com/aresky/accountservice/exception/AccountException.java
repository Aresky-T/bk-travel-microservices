package com.aresky.accountservice.exception;

public class AccountException extends RuntimeException {
    public static final AccountException INVALID_ACCOUNT_ID = new AccountException("Invalid account id");
    public static final AccountException INVALID_ACCOUNT_USERNAME = new AccountException("Invalid account username");
    public static final AccountException INVALID_ACCOUNT_EMAIL = new AccountException("Invalid account email");

    public static final AccountException ACCOUNT_ALREADY_EXIST = new AccountException("This account already exists");
    public static final AccountException ACCOUNT_NOT_FOUND = new AccountException("Account not found");
    public static final AccountException ACCOUNT_ROLE_CANNOT_BE_CHANGED = new AccountException("Không thể thay đổi vai trò của tài khoản này");
    public static final AccountException CANNOT_UPDATE_TO_ADMIN_ROLE = new AccountException("Cannot update to admin role");
    public static final AccountException CANNOT_UPDATE_ADMIN_ACCOUNT = new AccountException("Admin account cannot be update");


    public AccountException(String message) {
        super(message);
    }
}

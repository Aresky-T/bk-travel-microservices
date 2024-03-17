package com.aresky.accountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aresky.accountservice.service.IAccountService;

@CrossOrigin("*")
@RestController
@RequestMapping({ "/api/v1/accounts", "/account" })
public class AccountController {

    @Autowired
    private IAccountService accountService;

    public String home() {
        return "Welcome to Account service";
    }
}

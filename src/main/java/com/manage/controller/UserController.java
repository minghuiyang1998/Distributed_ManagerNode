package com.manage.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("/user/crack")
    public String crackPassword() {
        return "password";
    }
}

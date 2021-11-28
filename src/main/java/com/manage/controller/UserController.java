package com.manage.controller;

import com.manage.dao.NodesCenter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    NodesCenter nodesCenter = new NodesCenter();

    @PostMapping("/user/crack")
    public String crackPassword() {
        return "password";
    }
}

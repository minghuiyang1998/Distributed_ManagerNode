package com.manage.controller;

import com.manage.dao.NodesCenter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final NodesCenter nodesCenter;

    public UserController(NodesCenter nodesCenter) {
        this.nodesCenter = nodesCenter;
    }

    @PostMapping("/user/crack")
    public String crackPassword(RequestBody body) {
        System.out.println(body.getPasswordMd5());
        //......
        return "";
    }
}

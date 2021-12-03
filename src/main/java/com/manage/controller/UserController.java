package com.manage.controller;

import com.manage.dao.NodesCenter;
import com.manage.service.DistributeWork;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final NodesCenter nodesCenter;
    private final DistributeWork distributeWork;
    public UserController(NodesCenter nodesCenter, DistributeWork distributeWork) {
        this.nodesCenter = nodesCenter;
        this.distributeWork = distributeWork;
    }

    @PostMapping("/user/crack")
    public String crackPassword(RequestBody body) {
        String md5Password = body.getPasswordMd5();
        distributeWork.setMd5Password(md5Password);
        String res = distributeWork.distribute();
        // TODO: 12/3/21 return 
        return "";
    }
}

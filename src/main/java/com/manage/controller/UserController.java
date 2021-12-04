package com.manage.controller;

import com.manage.dao.NodesCenter;
import com.manage.service.DistributeWork;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class UserController {
    private final NodesCenter nodesCenter;
    private final DistributeWork distributeWork;
    public UserController(NodesCenter nodesCenter, DistributeWork distributeWork) {
        this.nodesCenter = nodesCenter;
        this.distributeWork = distributeWork;
    }

    @PostMapping("/user/crack")
    public String crackPassword(RequestBody body) throws ExecutionException, InterruptedException {
        String md5Password = body.getPasswordMd5();
        distributeWork.setMd5Password(md5Password);
        String res = distributeWork.distributeWork();
        // TODO: 12/3/21 return
        return "";
    }
}

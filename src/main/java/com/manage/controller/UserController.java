package com.manage.controller;

import com.manage.dao.NodesCenter;
import com.manage.service.DistributeWork;
import com.manage.service.ServiceConfig;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
    @ResponseBody
    public Map<String, String> crackPassword(RequestBody body) throws IOException, ExecutionException, InterruptedException {
        String md5Password = body.getPasswordMd5();
        distributeWork.setMd5Password(md5Password);
        System.out.println("md5Pwd: " + distributeWork.getMd5Password());
        String res = distributeWork.distributeWork();
        String[] parseStr = ServiceConfig.parse(res);
        Map<String, String> map = new HashMap<>();
        map.put("code", parseStr[0]);
        map.put("data", parseStr[1]);
        // TODO: 12/3/21 return
        return map;
    }
}

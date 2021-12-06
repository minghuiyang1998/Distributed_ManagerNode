package com.manage.controller;

import com.manage.dao.NodesCenter;
import com.manage.service.DistributeWork;
import com.manage.service.ServiceConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
    public UserResponse crackPassword(RequestBody body) throws IOException, ExecutionException, InterruptedException {
        String md5Password = body.getPasswordMd5();
        distributeWork.setMd5Password(md5Password);
        distributeWork.setSubtaskPrefix(ServiceConfig.START_PREFIX);
        System.out.println("md5Pwd: " + distributeWork.getMd5Password());
        String res = distributeWork.distributeWork();
        String[] parseStr = ServiceConfig.parse(res);
        return new UserResponse(parseStr[0], parseStr[1], parseStr[2]);
    }

    class UserResponse {
        private String code;
        private String msg;
        private String data;

        public UserResponse(String code, String msg, String data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public String getCode() {
            return code;
        }

        public String getData() {
            return data;
        }

        public String getMsg() {
            return msg;
        }
    }
}

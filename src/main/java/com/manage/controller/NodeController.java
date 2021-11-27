package com.manage.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class NodeController {
    @GetMapping("/admin/all")
    public String getAllWorkNodes() {
        // ajax
        return "all nodes";
    }

    @PostMapping("admin/node")
    public String addWorkNode(WorkNode body) {
        return body.getIP() + " " + body.getPort();
    }

    @DeleteMapping("/admin/node")
    public void deleteWorkNode(WorkNode body) {

    }
}

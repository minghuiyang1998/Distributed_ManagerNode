package com.manage.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NodeController {
    @GetMapping("/admin/all")
    public String getAllWorkNodes() {
        // ajax
        return "all nodes";
    }

    @PostMapping("admin/node")
    public String addWorkNode() {
        return "add nodes";
    }

    @DeleteMapping("/admin/node")
    public void deleteWorkNode() {
        //
    }
}

package com.manage.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
// code + msg
@RestController
public class NodeController {
    @GetMapping("/admin/all")
    public List<WorkNode> getAllWorkNodes() {
        // ajax
//        return "Nodes: [{ip: xxxx, port:xxxx}, {...}]]";
        ArrayList<WorkNode> workNodeArrayList = new ArrayList<WorkNode>();
        return workNodeArrayList;
    }

    @PostMapping("admin/node")
    public String addWorkNode(WorkNode body) {
        return body.getIP() + " " + body.getPort();
    }

    @DeleteMapping("/admin/node")
    public void deleteWorkNode(WorkNode body) {

    }
}

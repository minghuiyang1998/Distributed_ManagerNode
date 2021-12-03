package com.manage.controller;

import com.manage.dao.NodesCenter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
// code + msg
@RestController
public class NodeController {
    private final NodesCenter nodesCenter;

    public NodeController(NodesCenter nodesCenter) {
        this.nodesCenter = nodesCenter;
    }

    @GetMapping("/admin/all")
    public List<WorkNode> getAllWorkNodes() {
        // ajax
//        return "{
//          code： 0,
//          Nodes: [{ip: xxxx, port:xxxx}, {...}]]
//        }";
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

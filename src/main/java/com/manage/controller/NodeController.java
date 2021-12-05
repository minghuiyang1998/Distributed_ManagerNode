package com.manage.controller;

import com.manage.dao.NodesCenter;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
// code + msg
@RestController
public class NodeController {
    private final NodesCenter nodesCenter;

    public NodeController(NodesCenter nodesCenter) {
        this.nodesCenter = nodesCenter;
    }

    public List<WorkNode> getWorkNodeList() {
        List<WorkNode> workNodeList = nodesCenter.traverse();
        return workNodeList;
    }

    @GetMapping("/admin/all")
    @ResponseBody
    public NodeResponse getAllWorkNodes() {
        // ajax
//        return "{
//          code： 0,
//          Nodes: [{ip: xxxx, port:xxxx}, {...}]]
//        }";
        //
        return new NodeResponse(0, getWorkNodeList());
    }

    @PostMapping("admin/node")
    @ResponseBody
    public NodeResponse addWorkNode(WorkNode body) {
        int code = 0;
        try {
            nodesCenter.add(body);
        } catch (InterruptedException e) {
            e.printStackTrace();
            code = 1;   // the thread is interrupted
        }
        return new NodeResponse(code, getWorkNodeList());
//        return body.getIP() + " " + body.getPort();
    }

    @DeleteMapping("/admin/node")
    @ResponseBody
    public NodeResponse deleteWorkNode(WorkNode body) {
        int code = 0;
        if (!nodesCenter.remove(body)) {
            code = 2; // failed to delete the node
        }
        return new NodeResponse(code, getWorkNodeList());
    }

    class NodeResponse {
        private int code;
        private List<WorkNode> nodes;

        public NodeResponse(int code, List<WorkNode> nodes) {
            this.code = code;
            this.nodes = nodes;
        }

        public int getCode() {
            return code;
        }

        public List<WorkNode> getNodes() {
            return nodes;
        }
    }
}

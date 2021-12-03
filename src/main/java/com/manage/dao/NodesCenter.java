package com.manage.dao;

import com.manage.controller.WorkNode;
import org.springframework.stereotype.Repository;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Repository
public class NodesCenter {
    private static BlockingQueue<WorkNode> workNodeQueue = new LinkedBlockingQueue<>();

    public BlockingQueue<WorkNode> getWorkNodeQueue() {
        return workNodeQueue;
    }

    public void add(WorkNode workNode) {
        try {
            workNodeQueue.put(workNode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WorkNode get() {
        // dequeue head and return workNode
        WorkNode workNode = null;
        try {
            workNode = workNodeQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return workNode;
    }

}


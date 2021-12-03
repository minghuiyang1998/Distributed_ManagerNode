package com.manage.controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NodesCenter {
    private static NodesCenter instance;
    private BlockingQueue<WorkNode> workNodeQueue;

    public static NodesCenter getInstance() {
        if (instance == null) {
            instance = new NodesCenter();
        }
        return instance;
    }

    public BlockingQueue<WorkNode> getBlockingQueue() {
        return workNodeQueue;
    }

    public void addWorkNode(WorkNode workNode) {
        try {
            workNodeQueue.put(workNode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WorkNode deQueue() {
        // TODO:
        WorkNode workNode = null;
        try {
            workNode = workNodeQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return workNode;
    }

    private NodesCenter() {
        workNodeQueue = new LinkedBlockingQueue<>();
    }


}

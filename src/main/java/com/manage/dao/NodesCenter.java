package com.manage.dao;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Repository
public class NodesCenter {
    private static BlockingQueue<WorkNode> queue = new ArrayBlockingQueue<>(6);

    public void add() {

    }

    public boolean hasAvailableNode() {
        for(WorkNode node: queue) {
            if(node.isAvailable()) return true;
        }
        return false;
    }

    public WorkNode getAvailableNode() {
        for(WorkNode node: queue) {
            if(node.isAvailable()) return node;
        }
        return null;
    }

    public static BlockingQueue<WorkNode> getQueue() {
        return queue;
    }
    // static concurrent map
    // get
    // add
}

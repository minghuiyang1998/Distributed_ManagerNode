package com.manage.dao;

import com.manage.controller.WorkNode;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Repository
public class NodesCenter {
    private static BlockingQueue<WorkNode> workNodeQueue = new LinkedBlockingQueue<>();

    public BlockingQueue<WorkNode> getWorkNodeQueue() {
        return workNodeQueue;
    }

    public void add(WorkNode workNode) throws InterruptedException {
        workNodeQueue.put(workNode);
    }

    public boolean remove(WorkNode workNode) {
        return workNodeQueue.remove(workNode);
    }

    public WorkNode getHead() {
        // dequeue head and return workNode
        WorkNode workNode = null;
        try {
            workNode = workNodeQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return workNode;
    }

    public List<WorkNode> traverse() {
        // traverse BlockingQueue and return a list of nodes
        List<WorkNode> queue2List = new ArrayList<>();
        Iterator<WorkNode> iterator = workNodeQueue.iterator();
        while (iterator.hasNext()) {
            queue2List.add(iterator.next());
        }
        return queue2List;
    }

}


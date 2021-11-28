package com.manage.dao;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class NodesCenter {
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(6);

    public void add() {
        queue.add("xxxx");
    }

    // static concurrent map
    // get
    // add
}

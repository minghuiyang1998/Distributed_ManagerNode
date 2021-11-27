package com.manage.controller;

public class WorkNode {
    private final String ip;
    private final String port;

    public WorkNode(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIP() {
        return ip;
    }

    public String getPort() {
        return port;
    }
}

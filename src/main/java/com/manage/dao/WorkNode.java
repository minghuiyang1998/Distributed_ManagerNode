package com.manage.dao;

public class WorkNode {
    private final String ip;
    private final String port;
    private boolean available;

    public WorkNode(String ip, String port) {
        this.ip = ip;
        this.port = port;
        this.available = true;
    }

    public String getIP() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

package com.manage.service;

public class ServiceConfig {
    public static final String NOT_FOUND_MESSAGE = "1 Not Found";
    public static final String NO_AVAILABLE_NODES_MESSAGE = "2 No Available Nodes";
    public static final String FIND_PWD_MESSAGE = "0 ";
    public static final String START_PREFIX = "aa";
    public static final String END_DISTRIBUTE = "End";

    public static String[] parse(String res) {
        String code = res.substring(0, 1);
        String data = res.substring(2);
        return new String[]{code, data};
    }
}

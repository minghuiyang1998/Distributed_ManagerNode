package com.manage.service;

public class ServiceConfig {
    public static int TOTAL_TASK_SIZE = (int) Math.pow(52, 5);
    public static String NOT_FOUND_MESSAGE = "1 Not Found";
    public static String NO_AVAILABLE_NODES_MESSAGE = "2 No Available Nodes";
    public static String FIND_PWD_MESSAGE = "0 ";
    public static String END_PREFIX = "ZZ";

    public static String[] parse(String res) {
        String code = res.substring(0, 1);
        String data = res.substring(2);
        return new String[]{code, data};
    }
}

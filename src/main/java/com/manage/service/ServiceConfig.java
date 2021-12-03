package com.manage.service;

public class ServiceConfig {
    public static int totalTaskSize = (int) Math.pow(26, 5);
    public static int subTaskSize = (int) Math.pow(26, 3);

    public static String getPwdEnd(String subTaskPwdStart) {
        int end = get26To10(subTaskPwdStart) + subTaskSize - 1;
        return get10To26(end);
    }

    public static String add(String s, int i) {
        return get10To26(get26To10(s) + i);
    }

    public static int get26To10(String s) {
        int ret = 0;
        for(int i = 0; i < s.length(); i++) {
            ret = ret*26 + s.charAt(i) - 'a' + 1;
        }
        return ret;
    }

    public static String get10To26(int n) {
        String ret = "";
        while(n > 0) {
            int m = n % 26;
            if(m == 0) m = 26;
            ret = (char)(m+64) + ret;
            n = (n-m)/26;
        }
        return ret;
    }
}

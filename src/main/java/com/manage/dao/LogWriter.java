package com.manage.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
    private static FileWriter logWriter;

    public static void initLogFile() {
        try {
            File myObj = new File("log.txt");
            FileWriter logWriter = new FileWriter("log.txt", true);
            if (myObj.createNewFile()) {
                logWriter.write("Delay(ms)\tFound\n");
                logWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred when creating the log file.");
            e.printStackTrace();
        }
    }

    public static void write(String str) {
        try {
            logWriter.write(str + "\n");
            logWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

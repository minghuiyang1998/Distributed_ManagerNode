package com.manage.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {

    public static void initLogFile() {
        try {
            File myObj = new File("log.txt");
            if (myObj.createNewFile()) {
                FileWriter fw = new FileWriter("log.txt");
                fw.write("Delay(ms)\tFound\n");
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred when creating the log file.");
            e.printStackTrace();
        }
    }

    public static void write(String str) {
        try {
            FileWriter logWriter = new FileWriter("log.txt", true);
            logWriter.write(str + "\n");
            logWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

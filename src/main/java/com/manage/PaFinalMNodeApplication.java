package com.manage;

import com.manage.dao.LogWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaFinalMNodeApplication {
    public static void main(String[] args) {
        LogWriter.initLogFile();
        SpringApplication.run(PaFinalMNodeApplication.class, args);
    }
}

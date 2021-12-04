package com.manage.service;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Callable;

public class SocketThread implements Callable<String> {
    String ip;
    Socket socket;
    String dataSend;

    public SocketThread(String ip, int portNum, String dataSend) throws IOException {
        this.ip = ip;
        this.socket = new Socket(ip, portNum);
        this.dataSend = dataSend;
    }

    public void sendData() throws IOException {
        System.out.println("IP: " + this.ip + " Sending data: " + this.dataSend);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println(this.dataSend);
        printWriter.flush();
    }

    public String receiveData() throws IOException {
        // TODO: 12/3/21 Timer?
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String reply;
        reply = in.readLine();
        while(reply == null) {
            reply = in.readLine();
        }
        in.close();
        return reply;
    }

    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String call() throws IOException {
        sendData();
        System.out.println("IP: " + this.ip + " Massage sent successfully...");
        String subtaskRes = receiveData();
        System.out.println("IP: " + this.ip + " Received message " + subtaskRes);
        closeSocket();
        System.out.println("IP: " + this.ip + " Socket closed successfully...");
        return subtaskRes;
    }
}

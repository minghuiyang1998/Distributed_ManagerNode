package com.manage.service;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Callable;

public class SocketThread implements Callable<String> {
    Socket socket;
    String dataSend;
    OutputStream outputStream;
    InputStream inputStream;

    public SocketThread(String hostName, int portNum, String dataSend) {
        try {
            this.socket = new Socket(hostName, portNum);
            this.outputStream = socket.getOutputStream();
            this.inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dataSend = dataSend;
    }

    public void sendData() {
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.write(this.dataSend);
        printWriter.flush();
        printWriter.close();
    }

    public String receiveData() {
        // TODO: 12/3/21 Timer?
        String reply;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            if((reply = bufferedReader.readLine()) != null) {
                bufferedReader.close();
                return reply;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String call(){
        sendData();
        String subtaskRes = receiveData();
        closeSocket();
        return subtaskRes;
    }
}

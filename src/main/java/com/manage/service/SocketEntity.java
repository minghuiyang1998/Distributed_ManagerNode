package com.manage.service;

import java.io.*;
import java.net.Socket;

public class SocketEntity {
    Socket socket;
    OutputStream outputStream;
    InputStream inputStream;

    public SocketEntity(String hostName, int portNum) {
        try {
            this.socket = new Socket(hostName, portNum);
            this.outputStream = socket.getOutputStream();
            this.inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendData(String data) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.write(data);
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
}

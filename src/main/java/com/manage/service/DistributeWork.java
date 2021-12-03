package com.manage.service;

import com.manage.dao.WorkNode;
import com.manage.dao.NodesCenter;
import org.springframework.stereotype.Service;

@Service
public class DistributeWork {
    private String subtaskPwdStart;
    private int distributedSize;
    private String md5Password;

    public DistributeWork() {
        this.subtaskPwdStart = "aaaaa";
        this.distributedSize = 0;
    }

    public void setMd5Password(String md5Password) {
        this.md5Password = md5Password;
    }


    // TODO: 12/3/21 Concurrency Problem?
    public String distribute() {
        do {
            for (WorkNode node : NodesCenter.getQueue()) {
                if (node.isAvailable()) {
                    node.setAvailable(false);
                    SocketEntity socket = setUpConnection(node);
                    String subtaskPwdEnd = ServiceConfig.getPwdEnd(subtaskPwdStart);
                    String data = subtaskPwdStart + "," + subtaskPwdEnd + "," + md5Password;
                    socket.sendData(data);
                    String ret = socket.receiveData();
                    if (!ret.equals("Not found")) {
                        return ret;
                    }
                    socket.closeSocket();
                    node.setAvailable(true);
                    distributedSize += ServiceConfig.subTaskSize;
                    subtaskPwdStart = ServiceConfig.add(subtaskPwdEnd, 1);
                    if (distributedSize >= ServiceConfig.totalTaskSize) break;
                }
            }
        } while (distributedSize < ServiceConfig.totalTaskSize);
        return "Not found";
    }

    public SocketEntity setUpConnection(WorkNode node) {
        String ip = node.getIP();
        int port = Integer.parseInt(node.getPort());
        return new SocketEntity(ip, port);
    }
}

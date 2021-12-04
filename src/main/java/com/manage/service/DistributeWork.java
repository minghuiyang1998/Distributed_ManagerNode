package com.manage.service;

import com.manage.dao.WorkNode;
import com.manage.dao.NodesCenter;
import org.springframework.stereotype.Service;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class DistributeWork {
    Vector<Future<String>> futures;
    ExecutorService es;
    ArrayList<WorkNode> availableNodes;
    private String subtaskPrefix;
    private String md5Password;

    public DistributeWork() {
        this.subtaskPrefix = "aa";
        this.futures = new Vector<>();
        this.es = Executors.newCachedThreadPool();
        this.availableNodes = new ArrayList<>();
    }

    public void setMd5Password(String md5Password) {
        this.md5Password = md5Password;
    }

    // TODO: 12/3/21 Concurrency Problem?
    public String distributeWork() throws ExecutionException, InterruptedException {
        while(!subtaskPrefix.equals("ZZ")) {
//            System.out.println(subtaskPrefix);
            setAvailableNodes();
            if(noAvailableNodes()) return ServiceConfig.NO_AVAILABLE_NODES_MESSAGE;
            String message = subtaskPrefix + "," + md5Password;
            String s = distributeWorkOnce(message);
            if(!s.equals(ServiceConfig.NOT_FOUND_MESSAGE)) {
                s = ServiceConfig.FIND_PWD_MESSAGE + s;
                return s;
            }
        }
        return ServiceConfig.NOT_FOUND_MESSAGE;
    }

    public boolean noAvailableNodes() throws InterruptedException {
        int count = 0;
        while(availableNodes.size() == 0) {
            if(count == 2) return true;
            count++;
            Thread.sleep(1000);
            setAvailableNodes();
        }
        return false;
    }

    public void setAvailableNodes() {
        for(WorkNode workNode: NodesCenter.getQueue()) {
            if(workNode.isAvailable()) {
                availableNodes.add(workNode);
                workNode.setAvailable(false);
            }
        }
    }

    public String distributeWorkOnce(String message) throws ExecutionException, InterruptedException {
        for(WorkNode workNode: availableNodes) {
            String ip = workNode.getIP();
            int port = Integer.parseInt(workNode.getPort());
            SocketThread socketThread = new SocketThread(ip, port, message);
            Future<String> future = es.submit(socketThread);
            futures.add(future);
            if(subtaskPrefix.equals(ServiceConfig.END_PREFIX)) break;
            modifyPrefix();
        }

        es.shutdown();
        for(WorkNode workNode: availableNodes) {
            workNode.setAvailable(true);
        }
        availableNodes.clear();

        for(Future<String> future: futures) {
            String ret = future.get();
            if(!ret.equals(ServiceConfig.NOT_FOUND_MESSAGE)) {
                return ret;
            }
        }
        return ServiceConfig.NOT_FOUND_MESSAGE;
    }

    private void modifyPrefix() {
        StringBuilder strBuilder = new StringBuilder(subtaskPrefix);
        char first = subtaskPrefix.charAt(0);
        char second = subtaskPrefix.charAt(1);
        if(second == 'z') {
            strBuilder.setCharAt(1, 'A');
        } else if (second == 'Z') {
            if(first == 'z') {
                strBuilder.setCharAt(0, 'A');
                strBuilder.setCharAt(1, 'a');
            } else if(first == 'Z') {
                return;
            } else {
                strBuilder.setCharAt(0, (char)(first+1));
                strBuilder.setCharAt(1, 'a');
            }
        } else {
            strBuilder.setCharAt(1, (char) (second+1));
        }
        subtaskPrefix = strBuilder.toString();
    }

}

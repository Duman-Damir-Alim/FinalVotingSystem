package com.aitu.votingsystem.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Thread {

    public String processComplaintAndGetResponse(String complaintMessage) throws IOException, ClassNotFoundException, InterruptedException {
        InetAddress host = InetAddress.getLocalHost();
        Socket socket;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        socket = new Socket(host.getHostName(), 9876);
        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(complaintMessage);
        System.out.println("Sending request to Socket Server");
        Thread.sleep(2000);
        ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();
        System.out.println("Message: " + message);
        ois.close();
        oos.close();
        return message;
    }
}
package com.aitu.votingsystem.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int port = 9876;
        ServerSocket server = new ServerSocket(port);
        while (true) {
            System.out.println("Waiting for the client request");
            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Clients message: " + message);
            String outputText = "Help desk received your request, ";
            if (message.contains("bad") || message.contains("terrible")) {
                System.out.println("I am here");
                outputText += "sorry for such inconvenience, we will try to do better!";
            } else if (message.contains("good") || message.contains("great")) {
                outputText += "thank you, we will keep doing our best";
            } else {
                outputText += "ok, we will take it into consideration";
            }
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(outputText);
            ois.close();
            oos.close();
            socket.close();
            if (message.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        server.close();
    }
}
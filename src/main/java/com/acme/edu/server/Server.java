package com.acme.edu.server;

import com.acme.edu.Decorator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    static ExecutorService executeIt = Executors.newFixedThreadPool(2);
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void startServer() {
        Decorator decorator = new Decorator();
        String message = "";
        try (final ServerSocket connectionPortListener = new ServerSocket(port);
             final Socket clientConnection = connectionPortListener.accept();
             final DataInputStream input = new DataInputStream(
                     new BufferedInputStream(
                             clientConnection.getInputStream()));
             final DataOutputStream out = new DataOutputStream(
                     new BufferedOutputStream(
                             clientConnection.getOutputStream()))) {
            message = input.readUTF();
            message = decorator.decorate(message);
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

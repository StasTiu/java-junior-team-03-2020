package com.acme.edu.server;

import com.acme.edu.Decorator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    private static ExecutorService executeIt = Executors.newFixedThreadPool(2);
    private BlockingQueue<String> messages = new LinkedBlockingQueue<String>();
    private ArrayList<Socket> sockets = new ArrayList<>();
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void startServer() {
        try (ServerSocket server = new ServerSocket(port);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
             System.out.println("Server socket created, command console reader for listen to server commands");

            while (!server.isClosed()) {
                if (br.ready()) {
                    System.out.println("Main Server found any messages in channel, let's look at them.");

                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("quit")) {
                        System.out.println("Main Server initiate exiting...");
                        server.close();
                        break;
                    }
                }

                Socket client = server.accept();
                sockets.add(client);
                DataInputStream dis = new DataInputStream(
                        new BufferedInputStream(
                                client.getInputStream()));
                DataOutputStream dos = new DataOutputStream(
                        new BufferedOutputStream(
                                client.getOutputStream()));

                executeIt.execute(new MonoThreadClientHandler(client, dis, dos, sockets));
                System.out.print("Connection accepted.");
            }
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


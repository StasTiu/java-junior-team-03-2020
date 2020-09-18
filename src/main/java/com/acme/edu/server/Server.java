package com.acme.edu.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final ExecutorService executeIt = Executors.newCachedThreadPool();
    private final ArrayList<Socket> sockets = new ArrayList<>();
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

                executeIt.execute(new MonoThreadClientHandler(dis, this));
                System.out.print("Connection accepted.");
            }
            executeIt.shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(String message) {
        synchronized (sockets) {
            sockets.forEach(s -> {
                if (s.isClosed()) return;
                try {
                    OutputStream out = s.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(
                            new BufferedOutputStream(out));
                    dos.writeUTF(message);
                    dos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}


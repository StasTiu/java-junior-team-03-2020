package com.acme.edu.server;

import com.acme.edu.Command;
import com.acme.edu.ConsoleScanner;
import com.acme.edu.Decorator;
import com.acme.edu.FileSaver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

public class MonoThreadClientHandler implements Runnable {
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    //private final BlockingQueue<String> messages;
    private final ArrayList<Socket> sockets;
    private FileSaver saver = new FileSaver("history.txt");

    public MonoThreadClientHandler(Socket socket, DataInputStream inputStream, DataOutputStream outputStream, ArrayList<Socket> sockets) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.sockets = sockets;
    }

    @Override
    public void run() {
        Decorator decorator = new Decorator();
        String message = "";
        try (final Socket clientConnection = socket;
             final DataInputStream input = inputStream;
             final DataOutputStream out = outputStream) {

            while (true) {
                message = input.readUTF();
                System.out.println(message);
                ConsoleScanner scanner = new ConsoleScanner();
                Command command = scanner.parseCommand(message);
                switch (command.getType()) {
                    case SEND_COMMAND:
                        String response = decorator.decorate(command.getMessage());
                        /*synchronized (sockets) {
                            sendToAll(response);
                        }*/
                        send(response);
                        saver.save(response);
                        break;
//                    case ID_COMMAND:
//                        decorator = new Decorator(command.getMessage());
//                        continue;
                    case EXIT_COMMAND:
                        Thread.currentThread().interrupt();
                        continue;
                }
                out.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) throws IOException {
        outputStream.writeUTF(message);
    }

    public synchronized void sendToAll(String message) {
        Iterator<Socket> iter = sockets.iterator();
        while ( iter.hasNext() ) {
            Socket socket = iter.next();
            try {
                OutputStream out = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(out);
                writer.println(message);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
                iter.remove();
            }
        }
    }
}

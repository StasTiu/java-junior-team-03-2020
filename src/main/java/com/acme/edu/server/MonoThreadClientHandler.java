package com.acme.edu.server;

import com.acme.edu.Command;
import com.acme.edu.ConsoleScanner;
import com.acme.edu.Decorator;
import com.acme.edu.FileSaver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class MonoThreadClientHandler implements Runnable {
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private final BlockingQueue<String> messages;
    private FileSaver saver = new FileSaver("history.txt");

    public MonoThreadClientHandler(Socket socket, DataInputStream inputStream, DataOutputStream outputStream, BlockingQueue<String> messages) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.messages = messages;
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
                ConsoleScanner scanner = new ConsoleScanner();
                Command command = scanner.getCommandFromConsole();
                switch (command.getType()) {
                    case SEND_COMMAND:
                        String response = decorator.decorate(command.getMessage());
                        synchronized (message) {
                            messages.add(response);
                        }
                        out.writeUTF(response);
                        break;
                    case EXIT_COMMAND:
                        Thread.currentThread().interrupt();
                        continue;
                }
                saver.save(message);
                out.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
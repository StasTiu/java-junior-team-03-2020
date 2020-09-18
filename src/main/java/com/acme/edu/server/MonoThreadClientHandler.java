package com.acme.edu.server;

import com.acme.edu.Command;
import com.acme.edu.ConsoleScanner;
import com.acme.edu.Decorator;
import com.acme.edu.FileSaver;

import java.io.DataInputStream;
import java.io.IOException;

public class MonoThreadClientHandler implements Runnable {
    private final DataInputStream inputStream;
    private final Server server;
    private FileSaver saver = new FileSaver("history.txt");

    public MonoThreadClientHandler(DataInputStream inputStream, Server server) {
        this.inputStream = inputStream;
        this.server = server;
    }

    @Override
    public void run() {
        Decorator decorator = new Decorator();
        String message = "";
        try (final DataInputStream input = inputStream;) {

            while (true) {
                message = input.readUTF();
                ConsoleScanner scanner = new ConsoleScanner();
                Command command = scanner.parseCommand(message);
                switch (command.getType()) {
                    case SEND_COMMAND:
                        String response = decorator.decorate(command.getMessage());
                        server.sendToAll(response);
                        saver.save(response);
                        break;
                    case EXIT_COMMAND:
                        Thread.currentThread().interrupt();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

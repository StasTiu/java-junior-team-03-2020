package com.acme.edu.client;

import com.acme.edu.*;

import java.net.ServerSocket;
import java.io.IOException;
import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        Printer printer = new Printer();
        HistoryWriter writer = new HistoryWriter("history.txt");
        ConsoleScanner scanner = new ConsoleScanner();
        ConfigReader properties = new ConfigReader();

        String chid = "";

        try (
                final Socket connection = new Socket(properties.getHost(), properties.getPort());
                final DataOutputStream out = new DataOutputStream(
                        new BufferedOutputStream(
                        connection.getOutputStream()));
                final DataInputStream in = new DataInputStream(
                        new BufferedInputStream(
                                connection.getInputStream()));
        ) {
            Thread thread = new Thread(new OutputConsoleWriter(printer, in));
            thread.setDaemon(true);
            thread.start();

            while (!Thread.currentThread().isInterrupted()) {
                Command command = scanner.getCommandFromConsole();
                switch (command.getType()) {
                    case SEND_COMMAND:
                        if(command.getMessage().replaceAll(" ","").isEmpty()){
                            printer.print("empty message");
                        }else {
                            out.writeUTF(command.getType().getCommand() + " " + chid + ": " + command.getMessage());
                        }
                        break;
                    case ID_COMMAND:
                            chid = command.getMessage();
                            continue;
                        case EXIT_COMMAND:
                        Thread.currentThread().interrupt();
                        continue;
                    case HISTORY_COMMAND:
                        writer.write(command.getMessage());
                            continue;
                        case UNKNOWN_COMMAND:
                            printer.print("unknown command, try one more time");
                        continue;
                }
                out.flush();
            }
            thread.interrupt();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

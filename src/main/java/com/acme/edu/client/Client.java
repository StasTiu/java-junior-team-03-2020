package com.acme.edu.client;

import com.acme.edu.*;

import java.nio.*;
import java.io.IOException;
import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Printer printer = new Printer();
        HistoryWriter writer = new HistoryWriter("history.txt");
        ConsoleScanner scanner = new ConsoleScanner();
        boolean needExit = false;
        ConfigReader properties = new ConfigReader();

        try (final Socket connection = new Socket(properties.getHost(), properties.getPort());
             final DataInputStream input = new DataInputStream(
                     new BufferedInputStream(
                             connection.getInputStream()));
             final DataOutputStream out = new DataOutputStream(
                     new BufferedOutputStream(
                             connection.getOutputStream()));
        ) {
            while (!needExit) {
                do {
                    Command command = scanner.getCommandFromConsole();
                    switch (command.getType()) {
                        case SEND_COMMAND:
                            out.writeUTF(command.getType().getCommand() + " "  + command.getMessage());
                            break;
                        case ID_COMMAND:
                        out.writeUTF(command.getType().getCommand() + " "  + command.getMessage());
                        continue;
                    case EXIT_COMMAND:
                            needExit = true;
                            continue;
                        case HISTORY_COMMAND:
                            writer.write();
                        continue;
                    case UNKNOWN_COMMAND:
                        printer.print("unknown command, try one more time");
                            continue;
                    }
                    out.flush();
                } while (System.in.available() > 0);
                printer.print(input.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.acme.edu.client;

import com.acme.edu.Command;
import com.acme.edu.ConfigPropertyValues;
import com.acme.edu.ConsoleScanner;
import com.acme.edu.Printer;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Printer printer = new Printer();
        ConsoleScanner scanner = new ConsoleScanner();
        boolean needExit = false;
        ConfigPropertyValues properties = new ConfigPropertyValues();

        try (final Socket connection = new Socket("localhost", 10_000); //(properties.getHost(), properties.getPort());
             final DataInputStream input = new DataInputStream(
                     new BufferedInputStream(
                             connection.getInputStream()));
             final DataOutputStream out = new DataOutputStream(
                     new BufferedOutputStream(
                             connection.getOutputStream()));
        ) {

            while (!needExit) {
                Command command = scanner.getCommandFromConsole();
                switch (command.getType()) {
                    case SEND_COMMAND:
                        out.writeUTF(command.getMessage());
                        break;
                    case EXIT_COMMAND:
                        needExit = true;
                        continue;
                }
                out.flush();
                printer.print(input.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

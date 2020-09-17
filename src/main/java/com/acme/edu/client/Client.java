package com.acme.edu.client;

import com.acme.edu.*;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Printer printer = new Printer();
        ConsoleScanner scanner = new ConsoleScanner();
        boolean needExit = false;
        while (!needExit) {
            try (final Socket connection = new Socket("127.0.0.1", 10_000);
                 final DataInputStream input = new DataInputStream(
                         new BufferedInputStream(
                                 connection.getInputStream()));
                 final DataOutputStream out = new DataOutputStream(
                         new BufferedOutputStream(
                                 connection.getOutputStream()));
            ) {
                Command command = scanner.getCommandFromConsole();
                if (command instanceof SendCommand) {
                    out.writeUTF(((SendCommand) command).getMessage());
                } else if (command instanceof ExitCommand) {
                    needExit = true;
                    continue;
                }
                out.flush();
                printer.print(input.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

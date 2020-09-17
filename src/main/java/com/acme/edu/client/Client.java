package com.acme.edu.client;

import com.acme.edu.ConsoleScanner;
import com.acme.edu.Printer;

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
                String message = scanner.getMessageFromConsole();
                if (message.equals())
                    out.writeUTF(scanner.getMessageFromConsole());
                out.flush();
                printer.print(input.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

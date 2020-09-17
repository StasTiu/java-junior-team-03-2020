package com.acme.edu.server;

import com.acme.edu.Decorator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRunner {

    public static void main(String[] args) {
        Decorator decorator = new Decorator();
        String message = "";
        try (final ServerSocket connectionPortListener = new ServerSocket(10_000);
             final Socket clientConnection = connectionPortListener.accept();
             final DataInputStream input = new DataInputStream(
                     new BufferedInputStream(
                             clientConnection.getInputStream()));
             final DataOutputStream out = new DataOutputStream(
                     new BufferedOutputStream(
                             clientConnection.getOutputStream()))) {


            while (true) {
                message = input.readUTF();
                message = decorator.decorate(message);
                out.writeUTF(message);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.acme.edu.server;

import com.acme.edu.ConfigReader;

import java.io.*;

public class ServerRunner {

    public static void main(String[] args) throws IOException {
        ConfigReader properties = new ConfigReader();
        Server server  = new Server(properties.getPort());
        server.startServer();
    }
    /*
    public static void main(String[] args) {
        Decorator decorator = new Decorator();
        FileSaver saver = new FileSaver("history.txt");
        String message = "";
        ConfigReader properties = new ConfigReader();

        try (final ServerSocket connectionPortListener = new ServerSocket(properties.getPort());
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
                saver.save(message);
                out.writeUTF(message);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}

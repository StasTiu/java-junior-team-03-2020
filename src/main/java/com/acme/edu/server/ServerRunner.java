package com.acme.edu.server;

import com.acme.edu.ConfigReader;

import java.io.IOException;

public class ServerRunner {

    public static void main(String[] args) throws IOException {
        ConfigReader properties = new ConfigReader();
        Server server  = new Server(properties.getPort());
        server.startServer();
    }
}

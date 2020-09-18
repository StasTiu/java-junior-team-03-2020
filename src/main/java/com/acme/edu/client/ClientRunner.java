package com.acme.edu.client;

import com.acme.edu.ConfigReader;

public class ClientRunner {
    public static void main(String[] args) {
        ConfigReader properties = new ConfigReader();
        Client client = new Client(properties.getHost(), properties.getPort());
        client.start();
    }
}

package com.acme.edu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigPropertyValues {

    InputStream inputStream;

    public String getPropertyValueByName(String propertyName) throws IOException {
        String value = "";
        try {
            Properties prop = new Properties();
            String propFileName = "config/config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found");
            }

            value = prop.getProperty(propertyName);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return value;
    }

    public String getHost() throws IOException {
        String host = getPropertyValueByName("host");
        System.out.println("Started on host " + host);
        return host;
    }

    public int getPort() throws IOException {
        int port = Integer.parseInt(getPropertyValueByName("port"));
        System.out.println("Connected to port " + port);
        return port;
    }
}
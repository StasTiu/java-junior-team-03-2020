package com.acme.edu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Properties;

public class ConfigReader {

    private InputStream inputStream = null;

    private String getPropertyValueByName(String propertyName) throws IOException {
        String value = "";
        String propFileName = "application.properties";

        try {
            Properties prop = new Properties();
            inputStream = new FileInputStream(propFileName);
            prop.load(inputStream);

            value = prop.getProperty(propertyName);

        } catch (FileNotFoundException e) {
            System.out.println("Property file '" + propFileName + "' not found");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return value;
    }

    public String getHost()  {
        String host = "";
        try {
            host = getPropertyValueByName("host");
            if (host != null) {
                System.out.println("Host set to: " + host);
            } else {
                host = "127.0.0.1";
                throw new UnknownHostException("Cannot read host value from configuration file. Host set to default:" + host);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return host;
    }

    public int getPort() {
        int port = 10000;
        try {
            port = Integer.parseInt(getPropertyValueByName("port"));
            System.out.println("Port set to: " + port);
        } catch (Exception e) {
            System.out.println("Cannot read port value from configuration file. Port set to default: " + port);
        }
        return port;
    }
}
package com.acme.edu.client;

import com.acme.edu.Printer;

import java.io.DataInputStream;
import java.io.IOException;

public class OutputConsoleWriter implements Runnable {
    private final Printer out;
    private final DataInputStream in;

    public OutputConsoleWriter(Printer out, DataInputStream in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String inputData = in.readUTF();
                if (!inputData.isEmpty()) {
                    out.print(inputData);
                }
            }
        } catch (IOException e) {

        }

    }
}

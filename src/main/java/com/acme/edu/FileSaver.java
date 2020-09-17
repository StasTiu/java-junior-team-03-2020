package com.acme.edu;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileSaver {
    private final File fileInfo;

    public FileSaver(String fileInfo) {
        this.fileInfo = new File(fileInfo);
    }

    public void save(String message) throws IOException{
//        if (message == null) throw new FileLoggerSaverException("Empty message!");

        try (BufferedWriter bw =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     new BufferedOutputStream(
                                             new FileOutputStream(fileInfo, true)), StandardCharsets.UTF_8))) {
            bw.write(message + System.lineSeparator());

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Can't open File", e);
        }
    }
}

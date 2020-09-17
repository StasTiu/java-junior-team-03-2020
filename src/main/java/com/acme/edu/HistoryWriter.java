package com.acme.edu;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class HistoryWriter {
    private final File fileInfo;

    public HistoryWriter(String fileInfo) {
        this.fileInfo = new File(fileInfo);
    }

    public void write() throws IOException{
        try (BufferedReader br =
                     new BufferedReader(
                             new InputStreamReader(
                                     new BufferedInputStream(
                                             new FileInputStream(fileInfo)), StandardCharsets.UTF_8))){

            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                System.out.println(">> " + readLine + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Can't open File", e);
        }
    }
}

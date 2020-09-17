package com.acme.edu;


import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HistoryWriterTest implements SysoutCaptureAndAssertionAbility{
    private HistoryWriter sut;

    @Before
    public void setUp() throws IOException {
        sut = new HistoryWriter("testHistory.txt");
        FileSaver saver = new FileSaver("testHistory.txt");
        saver.save("test string 1");
        saver.save("test string 2");
        saver.save("test string 3");
        resetOut();
        captureSysout();
    }

    @Test
    public void messageIsPrintedToFileWhenSave() throws IOException {
        sut.write();

        assertSysoutContains("test string 1");
        assertSysoutContains("test string 2");
        assertSysoutContains("test string 3");
    }

    @Test(expected = NullPointerException.class)
    public void shouldTrowNPEIfFileNameIsNull() throws IOException {
        sut = new HistoryWriter(null);
        sut.write();
    }
}

package com.acme.edu;


import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FileSaverTest implements SysoutCaptureAndAssertionAbility {
    private FileSaver sut;

    @Before
    public void setUp() {
        sut = new FileSaver("test.txt");
        resetOut();
        captureSysout();
    }

    @Test
    public void messageIsPrintedToFileWhenSave() throws IOException {
        sut.save("test string 1");
        sut.save("test string 2");
        sut.save("test string 3");
        java.util.List<String> lines = Files.readAllLines(Paths.get("test.txt"));
        lines.forEach(System.out::println);

        assertSysoutContains("test string 1"+System.lineSeparator());
        assertSysoutContains("test string 2"+System.lineSeparator());
        assertSysoutContains("test string 3"+System.lineSeparator());
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionIfMessageIsNull() throws IOException {
        sut.save(null);
    }

    @Test(expected = NullPointerException.class)
    public void should() throws IOException {
        sut = new FileSaver(null);
        sut.save("HW!");
    }
}

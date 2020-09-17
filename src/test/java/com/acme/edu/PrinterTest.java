package com.acme.edu;

import org.junit.Before;
import org.junit.Test;

public class PrinterTest implements SysoutCaptureAndAssertionAbility{
    private Printer sut;

    @Before
    public void setUp(){
        sut = new Printer();
        resetOut();
        captureSysout();
    }

    @Test
    public void outShouldContainsMessage(){
        String message= "test";

        sut.print(message);
        assertSysoutEquals(message + System.lineSeparator());
    }
}

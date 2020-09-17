package com.acme.edu;

import org.junit.Before;
import org.junit.Test;

import static com.acme.edu.CommandType.SEND_COMMAND;

public class CommandTest implements SysoutCaptureAndAssertionAbility{
    private Command sut;

    @Before
    public void setUp(){
        sut = new Command(SEND_COMMAND, "test");
        resetOut();
        captureSysout();
    }

    @Test
    public void shouldReturnCorrectMessageWhenGetMessage(){
        System.out.println(sut.getMessage());

        assertSysoutContains("test");
    }

    @Test
    public void shouldReturnCorrectTypeWhenGetType(){
        System.out.println(sut.getType());

        assertSysoutContains("SEND_COMMAND");
    }
}

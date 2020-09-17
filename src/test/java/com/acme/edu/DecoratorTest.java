package com.acme.edu;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DecoratorTest implements SysoutCaptureAndAssertionAbility{
    private Decorator sut;

    @Before
    public void setUp(){
        sut = new Decorator();
        resetOut();
        captureSysout();
    }

    @Test
    public void outShouldContainsMessage(){
        String message= "test";
        System.out.println(sut.decorate(message));

        assertSysoutContains(message);
    }

    @Test
    public void outShouldContainsCorrectDate(){
        String message= "test";
        System.out.println(sut.decorate(message));
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        assertSysoutContains(formatter.format(date));
    }
}

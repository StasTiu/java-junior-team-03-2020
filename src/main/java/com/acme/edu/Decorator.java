package com.acme.edu;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Decorator {
    public String decorate(String message){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return message + ' ' + formatter.format(date);
    }
}

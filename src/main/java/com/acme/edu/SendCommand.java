package com.acme.edu;

public class SendCommand implements Command {
    private String message;

    SendCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute() {

    }
}

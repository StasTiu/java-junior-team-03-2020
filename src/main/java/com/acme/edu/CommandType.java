package com.acme.edu;

public enum CommandType {
    SEND_COMMAND("/snd"),
    HISTORY_COMMAND("/hist"),
    EXIT_COMMAND("/exit");


    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}

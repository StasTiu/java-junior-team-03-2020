package com.acme.edu;

public enum Command {
    SEND_COMMAND("/snd"),
    HISTORY_COMMAND("/hist"),
    EXIT_COMMAND("/exit");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}

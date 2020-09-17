package com.acme.edu;

public class Command {
    private String message = "";
    private CommandType type;

    public Command(CommandType type, String message) {
        this.message = message;
        this.type = type;
    }

    public Command(CommandType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public CommandType getType() {
        return type;
    }
}

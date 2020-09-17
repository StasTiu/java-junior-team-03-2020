package com.acme.edu;

import java.util.Scanner;

public class ConsoleScanner {

    public String getMessageFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return parseCommand(input);
    }

    public String parseCommand(String command) {
        if (command.contains(Command.SEND_COMMAND.getCommand() + " ")) {
            command = command.replace(Command.SEND_COMMAND + " ", "");
        } else if (command.equals(Command.HISTORY_COMMAND.getCommand())) {
            ///
        } else if (command.equals(Command.EXIT_COMMAND.getCommand())) {

        }
        return command;
    }
}

package com.acme.edu;

import java.util.Scanner;

public class ConsoleScanner {

    public Command getCommandFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return parseCommand(input);
    }

    public Command parseCommand(String input) {
        Command command = null;
        if (input.contains(CommandType.SEND_COMMAND.getCommand() + " ")) {
            String message = input.replace(CommandType.SEND_COMMAND.getCommand() + " ", "");
            command = new Command(CommandType.SEND_COMMAND, message);
        } else if (input.equals(CommandType.HISTORY_COMMAND.getCommand())) {
            ///
        } else if (input.equals(CommandType.EXIT_COMMAND.getCommand())) {
            command = new Command(CommandType.EXIT_COMMAND);
        } else {
            //
        }
        return command;
    }
}

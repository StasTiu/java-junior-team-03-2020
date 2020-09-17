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
            String message = input.replace(CommandType.SEND_COMMAND + " ", "");
            command = new SendCommand(message);
        } else if (input.equals(CommandType.HISTORY_COMMAND.getCommand())) {
            ///
        } else if (input.equals(CommandType.EXIT_COMMAND.getCommand())) {
            command = new ExitCommand();
        } else {
            //
        }
        return command;
    }
}

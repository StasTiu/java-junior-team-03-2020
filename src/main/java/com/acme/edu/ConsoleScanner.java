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
        } else if (input.contains(CommandType.HISTORY_COMMAND.getCommand() + " ")) {
            String date = input.replace(CommandType.ID_COMMAND.getCommand() + " ", "");
            command = new Command(CommandType.HISTORY_COMMAND,date);
        } else if (input.equals(CommandType.EXIT_COMMAND.getCommand())) {
            command = new Command(CommandType.EXIT_COMMAND);
        } else if(input.contains(CommandType.ID_COMMAND.getCommand() + " ")){
            String chid = input.replace(CommandType.ID_COMMAND.getCommand() + " ", "");
            command = new Command(CommandType.ID_COMMAND, chid);
        }else {
            command = new Command(CommandType.UNKNOWN_COMMAND);
        }
        return command;
    }
}

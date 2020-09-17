package com.acme.edu;

import java.util.Scanner;

public class ConsoleScanner {
    private static final String SEND_COMMAND = "/snd ";
    private static final String HISTORY_COMMAND = "/hist";

    public String getMessageFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return parseCommand(input);
    }

    public String parseCommand(String command) {
        if (command.contains(SEND_COMMAND)) {
            command = command.replace(SEND_COMMAND, "");
        } else if (command.equals(HISTORY_COMMAND)) {
            ///
        } else {
            ///
        }
        return command;
    }
}

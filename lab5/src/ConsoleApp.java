import CommandProvider.CommandManager;
import SpaceMarines.SpaceMarine;

import java.util.Scanner;

import static CommandProvider.Commands.Help.HelpCommand;


public class ConsoleApp {
    public static void main(String[] args) {
        Scanner cmd = new Scanner(System.in);
        CommandManager server = new CommandManager();
        HelpCommand();

        while(true){
            server.CommandChecker(cmd.nextLine());
        }
    }
}


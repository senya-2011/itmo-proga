import CommandProvider.CommandManager;

import static CommandProvider.Commands.Help.HelpCommand;
import static SpaceMarines.JustScanner.Scan;

/**
 * Main Class
 * <p>
 * This is a Main Class. Here we get the user string and give it to the CommandManager.
 * */
public class ConsoleApp {
    public static void main(String[] args) {
        CommandManager server = new CommandManager();
        HelpCommand();

        while(true){
            server.CommandChecker(Scan());
        }
    }
}


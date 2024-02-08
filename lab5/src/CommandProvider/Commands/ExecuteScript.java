package CommandProvider.Commands;

import CommandProvider.CommandManager;
import Data.DataProvider;

import java.util.Vector;

public class ExecuteScript {

    /**
     * execute script
     * <p>
     * read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively.
     *
     * @param fileName name of the file to be execute
     * @param dp Data Class that will read the file
     * */
    public static void ExecuteScriptCommand(String fileName, DataProvider dp){

        Vector<String> lines = dp.LoadScript(fileName);
        CommandManager cm = new CommandManager();

        for(String s: lines){
            cm.CommandChecker(s);
        }
    }
}


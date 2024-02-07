package CommandProvider.Commands;

import CommandProvider.CommandManager;
import Data.DataProvider;

import java.util.Vector;

public class ExecuteScript {
    public static void ExecuteScriptCommand(String fileName, DataProvider dp){

        Vector<String> lines = dp.LoadScript(fileName);
        CommandManager cm = new CommandManager();

        for(String s: lines){
            cm.CommandChecker(s);
        }
    }
}


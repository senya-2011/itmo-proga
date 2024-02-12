package CommandProvider;


import Data.DataProvider;
import SpaceMarines.SpaceMarine;

import java.util.LinkedList;
import java.util.Vector;

import static CommandProvider.Commands.Add.AddCommand;
import static CommandProvider.Commands.AddMax.AddMaxCommand;
import static CommandProvider.Commands.Clear.ClearCommand;
import static CommandProvider.Commands.CountLess.CountLessCommand;
import static CommandProvider.Commands.ExecuteScript.ExecuteScriptCommand;
import static CommandProvider.Commands.GroupByChapter.GroupByChapterCommand;
import static CommandProvider.Commands.Help.HelpCommand;
import static CommandProvider.Commands.History.HistoryCommand;
import static CommandProvider.Commands.Info.InfoCommand;
import static CommandProvider.Commands.Load.LoadCommand;
import static CommandProvider.Commands.Remove.RemoveCommand;
import static CommandProvider.Commands.Save.SaveCommand;
import static CommandProvider.Commands.Show.ShowCommand;
import static CommandProvider.Commands.Sort.SortCommand;
import static CommandProvider.Commands.Update.UpdateCommand;
import static SpaceMarines.JustScanner.Scan;

/**
 * Class responsible for commands
 * <p>
 * This is the brain of the program, which gives commands to other methods so that they perform certain actions.
 * */

public class CommandManager {
    private DataProvider dp = new DataProvider();
    private Vector<SpaceMarine> collection = new Vector<SpaceMarine>();
    LinkedList commandList = new LinkedList();
    CompareSpaceMarine CSM = new CompareSpaceMarine();

    /**
     *Check for Command
     * <p>
     * We check the user's line for the presence of a command, and if available, pass it to the commands
     *
     * @param userString this is the string that the user entered
     * */
    public void CommandChecker(String userString){

        String[] commandToWords = userString.split(" ");
        String command = commandToWords[0]; // получаем первое слово, что и является коммандой


        if (command.equals("help") && commandToWords.length==1){
            HelpCommand();
            addCommand(command);
        }else
        if (command.equals("info") && commandToWords.length==1){
            InfoCommand(collection);
            addCommand(command);
        }else
        if (command.equals("add") && commandToWords.length==1){ //не совсем понятно, что за элемент надо добавлять по этому мы создаем его
            AddCommand(collection, CSM);
            addCommand(command);
        }else
        if (command.equals("show") && commandToWords.length==1){
            ShowCommand(collection);
            addCommand(command);
        }else
        if (command.equals("clear") && commandToWords.length==1){
            ClearCommand(collection);
            addCommand(command);
        }else
        if (command.equals("update") && commandToWords.length==3){
            UpdateCommand(collection, commandToWords);
            addCommand(command);
        }else
        if (command.equals("remove_by_id") && commandToWords.length==2){
            RemoveCommand(collection, commandToWords);
            addCommand(command);
        }else
        if (command.equals("exit") && commandToWords.length==1){
            System.exit(0);
            addCommand(command);
        }else
        if (command.equals("sort") && commandToWords.length==1){
            collection = SortCommand(collection);
            addCommand(command);
        }else
        if (command.equals("save") && commandToWords.length==1){
            dp.Save(collection, SaveCommand(collection));
            System.out.println("Сохранено");
            addCommand(command);
        }else
        if (command.equals("load") && commandToWords.length==1){
            collection = LoadCommand(dp);
            addCommand(command);
        }else
        if (command.equals("history") && commandToWords.length==1){
            HistoryCommand(commandList);
            addCommand(command);
        }else
        if(command.equals("group_counting_by_chapter") && commandToWords.length==1){
            GroupByChapterCommand(collection);
            addCommand(command);
        }else
        if(command.equals("count_less_than_chapter") && commandToWords.length==2){
            CountLessCommand(collection, commandToWords[1]);
            addCommand(command);
        }else
        if(command.equals("count_greater_than_weapon_type weaponType") && commandToWords.length==2){
            CountLessCommand(collection, commandToWords[1]);
            addCommand(command);
        }else
        if(command.equals("add_if_max") && commandToWords.length==1){
            AddMaxCommand(collection, CSM);
            addCommand(command);
        }else
        if(command.equals("cls") && commandToWords.length==1){
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); //очистка консоли
        }else
        if(userString.replaceAll(" ", "")==""){
            System.out.print("");//не выдаем ошибку на пустую строку
        }else
        if(command.equals("execute_script") && commandToWords.length==2){
            ExecuteScriptCommand(commandToWords[1], dp);
            addCommand(command);
        }else{
            System.out.println("Такой команды нет. Введите help для списка команд");
        }

    }

    /**
     * This is a command counter that is needed to clear the list of them
     *
     * @param command - last command entered by the user
     * */
    private void addCommand(String command) {
        if (commandList.size() == 6) {
            commandList.removeFirst(); // удаление первого элемента, если достигнут максимальный размер
        }
        commandList.addLast(command);
    }
}

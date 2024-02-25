package main.Managers;

import interfaces.Command;
import main.Commands.*;
import main.ResponseAndRequest.Request;
import main.SpaceMarines.SpaceMarine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Invoker {
    CollectionManager collectionManager;
    private Map<String, Command> commands = new HashMap<>();

    CollectionManager cm;
    LinkedList commandList = new LinkedList();

    public Invoker(CollectionManager cm){
        collectionManager = cm;
        commands.put("help", new HelpCommand());
        commands.put("add", new AddCommand(cm));
        commands.put("show", new ShowCommand(cm));
        commands.put("clear", new ClearCommand(cm));
        commands.put("info", new InfoCommand(cm));
        commands.put("add_if_max", new AddMaxCommand(cm));
        commands.put("count_less_than_chapter", new CountLessCommand(cm));
        commands.put("count_greater_than_weapon_type", new CountWeaponCommand(cm));
        commands.put("group_counting_by_chapter", new GroupByChapterCommand(cm));
        commands.put("history", new HistoryCommand(commandList));
        commands.put("remove_by_id", new RemoveCommand(cm));
        commands.put("sort", new SortCommand(cm));
        commands.put("update", new UpdateCommand(cm, getId()));
    }


    public String doCommand(Request request){
        addCommand(request.getName());
        checkArg(request);

        Command command = commands.get(request.getName());
        command.execute();
        return command.descr();
    }

    private void addCommand(String command) {
        if (commandList.size() == 6) {
            commandList.removeFirst(); // удаление первого элемента, если достигнут максимальный размер
        }
        commandList.addLast(command);
    }

    private void checkArg(Request request){
        if(request.getSp()!=null){
            SpaceMarine sp = request.getSp();
            sp.setId(getId());
            sp.setCreationDate();
            collectionManager.setSpaceMarine(sp);
        }
        if(request.getId()!=null){
            collectionManager.setArg(request.getId());
        }
    }
    private int getId(){
        int id;
        int length;
        while(true){
            length = 0;
            id= (int) (Math.random() * 100000000)+1;
            if(collectionManager.getCollection().size()==0){
                return id;
            }
            for(SpaceMarine sp: collectionManager.getCollection()){
                if(sp.getId()!=id){
                    length++;
                }
            }
            if(length==collectionManager.getCollection().size()){
                return id;
            }
        }
    }
}

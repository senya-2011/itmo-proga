package main.Data.Commands;


import interfaces.Command;
import main.Managers.CollectionManager;

public class ShowCommand implements Command {
    private CollectionManager cm;
    private String description;

    public ShowCommand(CollectionManager cm){
        this.cm = cm;
    }


    @Override
    public String descr(){
        return description;
    }
    @Override
    public void execute(){
        StringBuilder s = new StringBuilder();

        cm.getCollection().forEach(sp -> s.append(sp).append("\n--------------\n"));
        description = String.valueOf(s);
    }
}

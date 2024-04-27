package main.Data.Commands;

import interfaces.Command;
import main.Managers.CollectionManager;

public class MyCommand implements Command {
    CollectionManager cm;
    String description;

    public MyCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public String descr(){
        return description;
    }
    @Override
    public void execute(){
        StringBuilder s = new StringBuilder();

        cm.getCollection().stream().filter(sp -> sp.getUser().equals(cm.getLogin()))
                .forEach(sp -> s.append(sp).append("\n--------------\n"));
        description = String.valueOf(s);
    }
}

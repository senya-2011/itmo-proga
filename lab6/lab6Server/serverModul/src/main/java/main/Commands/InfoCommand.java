package main.Commands;

import interfaces.Command;
import main.Managers.CollectionManager;

public class InfoCommand implements Command {

    private int size;
    private CollectionManager cm;

    public InfoCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {
        size = cm.getCollection().size();
    }

    @Override
    public String descr() {
        return "Размер коллекции: "+size;
    }
}

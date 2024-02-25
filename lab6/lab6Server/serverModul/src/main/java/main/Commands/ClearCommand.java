package main.Commands;


import interfaces.Command;
import main.Managers.CollectionManager;

public class ClearCommand implements Command {

    private CollectionManager cm;

    public ClearCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {
        cm.clearCollection();
    }

    @Override
    public String descr() {
        return "коллекция была очищена.";
    }
}

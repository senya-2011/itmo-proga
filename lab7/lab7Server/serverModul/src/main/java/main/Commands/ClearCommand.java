package main.Commands;


import interfaces.Command;
import main.Managers.CollectionManager;
import main.SpaceMarines.SpaceMarine;

import java.util.Vector;

public class ClearCommand implements Command {

    private CollectionManager cm;

    public ClearCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {
        Vector<SpaceMarine> vector = new Vector<>();
        cm.getCollection().stream()
                        .filter(sp->!sp.getUser().equals(cm.getLogin()))
                                .forEach(sp-> vector.add(sp));
        cm.setCollection(vector);
    }

    @Override
    public String descr() {
        return "коллекция была очищена.";
    }
}

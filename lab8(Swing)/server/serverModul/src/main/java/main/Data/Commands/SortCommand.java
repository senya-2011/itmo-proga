package main.Data.Commands;

import interfaces.Command;
import main.Managers.CollectionManager;
import main.SpaceMarines.SpaceMarine;

import java.util.Collections;
import java.util.Vector;

public class SortCommand implements Command {

    private CollectionManager cm;

    public SortCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {
        Vector<Integer> idVector = new Vector<>();
        Vector<SpaceMarine> sortedCollection = new Vector<>();

        cm.getCollection().forEach(sp -> idVector.add(sp.getId()));
        Collections.sort(idVector); //получаем сортированный вектор по значениям

        idVector.forEach(id -> cm.getCollection().stream()
                .filter(sp -> sp.getId()==id)
                .forEach(sp -> sortedCollection.add(sp)));

        cm.setCollection(sortedCollection);
    }

    @Override
    public String descr() {
        return "коллекция была сортирована по id.";
    }
}

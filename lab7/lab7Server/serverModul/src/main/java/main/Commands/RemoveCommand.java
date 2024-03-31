package main.Commands;

import interfaces.Command;
import main.Managers.CollectionManager;
import main.SpaceMarines.SpaceMarine;

public class RemoveCommand implements Command {

    private CollectionManager cm;
    private String descr;


    public RemoveCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {
        StringBuilder descr = new StringBuilder();
        int id = Integer.parseInt(cm.getArg());
        cm.getCollection().stream()
                .filter(sp -> sp.getId()==id && sp.getUser().equals(cm.getLogin()))
                .forEach(sp -> this.descr = String.valueOf(descr.append("Успешно удален SpaceMarine с id: ").append(id)));
        cm.getCollection().removeIf(sp -> sp.getId() == id);

        if(this.descr==null){
            this.descr = "Вашего SpaceMarine с таким айди нету!";
        }
    }

    @Override
    public String descr() {
        return descr;
    }
}

package main.Commands;


import interfaces.Command;
import main.Managers.CollectionManager;

public class AddCommand implements Command {
    private CollectionManager cm;

    public AddCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public String descr(){
        return "Элемент был добавлен в коллекцию.";
    }
    @Override
    public void execute(){
        cm.addActiveUser(cm.getLogin());
        cm.addCollection();
    }
}

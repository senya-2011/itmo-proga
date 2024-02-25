package main.Commands;


import interfaces.Command;
import main.Managers.CollectionManager;

public class AddMaxCommand implements Command {

    private CollectionManager cm;
    private String descr;

    public AddMaxCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {

        boolean add = cm.getCollection().stream()
                .noneMatch(sp -> sp.getId()>cm.getSp().getId());

        if (cm.getCollection().size()==0 || add){
            cm.addCollection();
            descr = "Сгенирировался максимальный id - добавляем.";
        }else{
            descr = "Сгенирировался не максимлаьный id - не добавляем.";
        }
    }

    @Override
    public String descr() {
        return descr;
    }
}

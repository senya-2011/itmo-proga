package main.Commands;

import interfaces.Command;
import main.Managers.CollectionManager;

public class UpdateCommand implements Command {

    private CollectionManager cm;
    private String descr;
    private int updateID;

    public UpdateCommand(CollectionManager cm, int updateID){
        this.cm = cm;
        this.updateID = updateID;
    }


    @Override
    public void execute() {
        boolean isNotId = true;
        StringBuilder s = new StringBuilder();
        int id = Integer.parseInt(cm.getArg());

        cm.getCollection().stream()
                .filter(sp -> id == sp.getId())
                .forEach(sp ->{
                    sp.setId(updateID);
                    s.append("Значение обновленно! Новое значение: ").append(String.valueOf(sp.getId()));
                    descr = String.valueOf(s);
                } );

        if(descr==null){
            this.descr = "SpaceMarine с таким id нету!";
        }
    }

    @Override
    public String descr() {
        return descr;
    }
}

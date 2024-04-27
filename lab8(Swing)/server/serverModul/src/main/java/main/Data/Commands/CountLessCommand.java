package main.Data.Commands;


import interfaces.Command;
import main.Managers.CollectionManager;
import main.SpaceMarines.SpaceMarine;

public class CountLessCommand implements Command {

    private CollectionManager cm;
    private int count;

    public CountLessCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {
        int chapterValue = Integer.parseInt(cm.getArg());
        int count = 0;
        for (SpaceMarine sp : cm.getCollection()) {
            if (sp.getChapter() == null) {
                count++;
            } else {
                if (sp.getChapter().getMarinesCount() < chapterValue) {
                    count++;
                }
            }
        }
        this.count = count;
    }

    @Override
    public String descr() {
        return "Кол-во элементов, значение поля chapter которых меньше заданного: "+count;
    }
}

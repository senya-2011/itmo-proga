package main.Commands;


import interfaces.Command;
import main.Managers.CollectionManager;

public class CountLessCommand implements Command {

    private CollectionManager cm;
    private int count;

    public CountLessCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {
        int chapterValue = Integer.parseInt(cm.getArg());

        this.count = (int) cm.getCollection().stream()
                .filter(sp -> sp.getChapter().getMarinesCount()<chapterValue)
                .count();
    }

    @Override
    public String descr() {
        return "Кол-во элементов, значение поля chapter которых меньше заданного: "+count;
    }
}

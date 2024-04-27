package main.Data.Commands;

import interfaces.Command;
import main.Managers.CollectionManager;

import java.util.HashMap;
import java.util.Map;

public class GroupByChapterCommand implements Command {

    private CollectionManager cm;
    private String descr;

    public GroupByChapterCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {
        StringBuilder descr = new StringBuilder();
        Map<Integer, Integer> groupsMap = new HashMap<>();//key - уникальное значение, 2-ой Integer - кол-во повторений
        //за значение Chapter возьмем marinesCount


        int nullCount = (int) cm.getCollection().stream()
                .filter(sp -> sp.getChapter() == null)
                .count();

        cm.getCollection().stream().filter(sp -> sp.getChapter()!=null).
    forEach(sp -> UpdateGroupsMap(groupsMap, sp.getChapter().getMarinesCount()));

        groupsMap.forEach((key, value) -> descr.append("Количество Chapter со значением ").append(key).append(" = ").append(value).append("\n"));

        descr.append("Количество Chapter со значением null = ").append(nullCount);

        this.descr = String.valueOf(descr);
    }
    private static void UpdateGroupsMap(Map<Integer, Integer> groupMap, int value){
        if(groupMap.containsKey(value)){ //если группа с таким номером уже сущесствует
            groupMap.put(value, groupMap.get(value)+1);//добавляем к кол-ву повторений 1
        }else{
            groupMap.put(value, 1);//если нету создаем новую группу с 1
        }
    }

    @Override
    public String descr() {
        return descr;
    }
}

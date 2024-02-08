package CommandProvider.Commands;

import SpaceMarines.SpaceMarine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class GroupByChapter {


    /**
     * group elements by value of chapter
     * <p>
     * group the collection elements by the value of the chapter field, display the number of elements in each group
     *
     * @param collection collection where we store SpaceMarine objects
     * */
    public static void GroupByChapterCommand(Vector<SpaceMarine> collection){

        int nullCount=0;
        Map<Integer, Integer> groupsMap = new HashMap<>();//key - уникальное значение, 2-ой Integer - кол-во повторений
        //за значение Chapter возьмем marinesCount

        for(SpaceMarine sp: collection){
            try{
                int value = sp.getChapter().getMarinesCount(); //получаем значение каждого
                UpdateGroupsMap(groupsMap, value);
            }catch (NullPointerException e){
                nullCount++;            }
        }

        for(Map.Entry<Integer, Integer> e:groupsMap.entrySet() ){ //разбиваем на пары "ключ+значение" и проходимся по каждой
            System.out.println("Количество Chapter со значением "+ e.getKey()+" = "+e.getValue());
        }
        System.out.println("Количество Chapter со значением null = "+nullCount);
    }

    private static void UpdateGroupsMap(Map<Integer, Integer> groupMap, int value){
        if(groupMap.containsKey(value)){ //если группа с таким номером уже сущесствует
            groupMap.put(value, groupMap.get(value)+1);//добавляем к кол-ву повторений 1
        }else{
            groupMap.put(value, 1);//если нету создаем новую группу с 1
        }
    }
}

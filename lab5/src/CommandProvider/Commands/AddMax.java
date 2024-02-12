package CommandProvider.Commands;

import CommandProvider.CompareSpaceMarine;
import SpaceMarines.SpaceMarine;

import java.util.Collections;
import java.util.Vector;

public class AddMax {

    /**
     * add if element value is max
     * <p>
     * add a new element to a collection if its value is greater than the value of the largest element of this collection
     *
     * @param collection collection where we store SpaceMarine objects
     * @param CSM Comparator to sort
     * */
    public static void AddMaxCommand(Vector<SpaceMarine> collection, CompareSpaceMarine CSM){
        SpaceMarine sp = new SpaceMarine();
        System.out.println("Созданный персонаж");
        System.out.println(sp);
        int maxId=0;

        for(SpaceMarine s: collection){
            if(s.getId()>maxId){
                maxId = s.getId();
            }
        }
        System.out.println("Максимальное значение: "+ String.valueOf(maxId));
        System.out.println("Значение созданого: "+ String.valueOf(sp.getId()));
        if(sp.getId()>maxId){
            System.out.println("Добавляем!");
            collection.add(sp);
            Collections.sort(collection, CSM);
        }else{
            System.out.println("Не добавляем!");
        }
    }
}

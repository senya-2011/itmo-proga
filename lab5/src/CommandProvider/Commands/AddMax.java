package CommandProvider.Commands;

import SpaceMarines.SpaceMarine;

import java.util.Vector;

public class AddMax {
    public static void AddMaxCommand(Vector<SpaceMarine> collection){
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
        }else{
            System.out.println("Не добавляем!");
        }
    }
}

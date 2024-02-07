package CommandProvider.Commands;

import SpaceMarines.SpaceMarine;

import java.time.LocalDateTime;
import java.util.Vector;

public class Info {
    private LocalDateTime Date;

    public static void InfoCommand(Vector<SpaceMarine> collection){
        System.out.println("Размер коллекции: "+ String.valueOf(collection.size()));
        if(collection.size()>0) {
            LocalDateTime maxDate = collection.get(0).getDate();

            for (SpaceMarine sp : collection) {
                if (maxDate.isBefore(sp.getDate())) {
                    maxDate = sp.getDate();
                }
            }
            System.out.println("Дата последнего изменения коллекции: "+maxDate);
        }

        System.out.println("Кол-во элементов: "+collection.size());

    }
}

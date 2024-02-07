package CommandProvider.Commands;

import SpaceMarines.SpaceMarine;

import java.util.Vector;

public class Update {
    public static void UpdateCommand(Vector<SpaceMarine> collection, String[] commandToWords) {

        if (commandToWords[1]!="id") {
            System.out.println("Ошибка ввода команды!");
        } else {
            try {
                boolean isId = true;
                int cmdId = Integer.parseInt(commandToWords[2]);
                for(SpaceMarine o: collection){
                    if (cmdId==o.getId()){
                        o.setId();
                        System.out.println("Значение обновленно! Новое значение: "+ String.valueOf(o.getId()));
                        isId = false;
                    }
                }
                if(isId){
                    System.out.println("SpaceMarine с таким id нету!");
                }



            } catch (NumberFormatException e) {
                System.out.println("Ошибка id - int!");
            }
        }
    }
}

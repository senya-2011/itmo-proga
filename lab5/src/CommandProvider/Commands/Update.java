package CommandProvider.Commands;

import SpaceMarines.SpaceMarine;

import java.util.Vector;

public class Update {
    /**
     * update value of element
     * <p>
     * update the value of a collection element whose id is equal to a given one
     *
     * @param collection collection where we store SpaceMarine objects
     * @param commandToWords the string entered by the user, divided into words
     * */

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

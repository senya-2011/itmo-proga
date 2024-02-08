package CommandProvider.Commands;

import SpaceMarines.SpaceMarine;

import java.util.Vector;

public class CountLess {

    /**
     * display quantity less than specified
     * <p>
     * display the number of elements whose chapter field value is less than a given one
     *
     * @param collection collection where we store SpaceMarine objects
     * @param chapterString command argument meaning chapter value
     * */

    public static void CountLessCommand(Vector<SpaceMarine> collection, String chapterString){

        int count=0;

        try{
            int chapterValue = Integer.parseInt(chapterString);
            for(SpaceMarine sp: collection){
                int value = sp.getChapter().getMarinesCount(); //получаем значение каждого
                if(value<chapterValue){
                    count++;
                }
            }
        }catch(NumberFormatException e){
            System.out.println("Надо ввести значение chapter int!");
        }
        System.out.println("Кол-во элементов, значение поля chapter которых меньше заданного: "+count);
    }
}

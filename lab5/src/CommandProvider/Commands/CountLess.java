package CommandProvider.Commands;

import SpaceMarines.SpaceMarine;

import java.util.Vector;

public class CountLess {
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

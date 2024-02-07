package SpaceMarines;

import java.io.Serializable;

import static SpaceMarines.JustScanner.Scan;

public class Coordinates implements Serializable {
    private int x;
    private Long y; //Поле не может быть null
    public Coordinates(){
        while(true){
            try{
                System.out.print("Введите (int)х: ");
                x = Integer.parseInt(Scan());
                break;
            }catch(NumberFormatException e){
                System.out.println("Надо ввести значение int!");
            }
        }

        while(true){
            try{
                System.out.print("Введите (Long)y: ");
                y = Long.parseLong(Scan());
                break;
            }catch(NumberFormatException e){
                System.out.println("Надо ввести значение Long!");
            }
        }


    }
    @Override
    public String toString(){
        return ("x: "+String.valueOf(x)+", y: "+String.valueOf(y));
    }
}

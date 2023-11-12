package Objects;

import Enums.Place;
import Interfaces.IProperty;

public class Thing extends Obj implements IProperty {

    private String name;
    public Thing(String name) {
        super(name);
        this.name =  name;
    }

    public static class EndSentence{ //статик вложенный класс
        public static void endMessage(String str){
            System.out.print(str+ " ");
        }
    }

    @Override
    public void setProperty(String str, Place place) {
        if(place == Place.BEFORE){
            this.name = str + " " + super.name + " ";
        }else{ //понятное дело тут могут быть другие плейсы кроме AFTER, но проверку на это делать не стал тк зачем пользователь же ничего не вводит
            this.name = super.name+ " " +  str + " ";
        }
        System.out.print(this.name);
    }
}

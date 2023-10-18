package objects;

import Enums.Action;
import Enums.CoordinateEnum;
import Enums.Gender;
import interfaces.Coordinate;
import interfaces.Property;

import java.util.Locale;
import java.util.Objects;

public class Entity extends Obj implements Property, Coordinate {

    //проверка игнорирующая регистр
    @Override
    public boolean equals(Object o) {
        Entity entity = (Entity) o;
        return Objects.equals(ending, entity.ending) && Objects.equals(name.toLowerCase(), entity.name.toLowerCase());
    }

    //сравнение по полу
    @Override
    public int hashCode() {
        return Objects.hash(ending);
    }

    //вывод имени и пола объекта
    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }

    private String ending;
    final private String name;
    final private Gender sex;

    public Entity(String name, Gender sex){
        super(name);
        this.name = name;
        this.sex = sex;
        this.ending = sex.getName();
    }

    public void doSomething(Action act){
        System.out.print(super.name+" ");
        if (act == Action.noDelete){
            System.out.print("нельзя убрать");
        }else if (act == Action.Spin){
            System.out.print("кружил"+ending);
        }else if (act == Action.Kylbit){
            System.out.print("делал"+ending+" в воздухе небольшой кульбит");
        }else if (act == Action.Talk){
            System.out.print("на замолкал"+ending);
        }
    }

    public void doSomethingAboutSb(Action act, Obj e){
        if (act == Action.NotListen){
            System.out.print(super.name + " не слушал"+ ending + e.name+"a");//добавляем окончание взависимости от пола
        }else if (act == Action.Watch){
            System.out.print(super.name + " смотрел"+ ending + e.name);
        }
    }
    @Override
    public void getProperty(String property){
        super.name = this.name;
        super.name = property+" "+super.name;}

    @Override
    public void getCoordinate(CoordinateEnum xyz){
        if (xyz == CoordinateEnum.NoCoordinate){
            super.name = super.name;
        }else if (xyz == CoordinateEnum.All){
            super.name = this.name;
            super.name = super.name+", удалялось, вновь приближалось, то взмывая ввысь, то спускаясь пониже, ";
        }
    }
    @Override
    public void getCoordinate(Place place){
        super.name = super.name + " в " + place.name;
    }
}

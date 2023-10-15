import Enums.Action;
import Enums.CoordinateEnum;
import Enums.Gender;
import Enums.Time;
import objects.Entity;
import objects.Place;
import objects.PlaceTime;
import objects.Thing;

public class Main {


    public static void main(String[] args){
        //объекты, которые могут действовать
        Entity karl = new Entity("Малыш", Gender.Male);
        Entity bok = new Entity("Фрекен Бок", Gender.Female);
        Entity sight = new Entity("взгляд", Gender.Male);
        Entity ghost = new Entity("Привидение", Gender.Neuther);
        Entity sounds = new Entity("звуки", Gender.All);

        //объекты не умеющие действовать
        Thing window = new Thing("окно");
        Thing ghostFlight = new Thing("полет привидения");

        //объекты места
        Place city = new Place("Вазастан");
        Place sky = new Place("воздух");

        System.out.println("=================================================================================\n");
        //1 предложение
        bok.doSomethingAboutSb(Action.NotListen, karl);
        System.out.print(". ");

        //2 предложение
        sight.getProperty("Ее обезумевший");
        window.getProperty("на");
        ghostFlight.getProperty("на причудливый");
        sight.doSomethingAboutSb(Action.Watch, window);
        System.out.print(", ");
        bok.doSomethingAboutSb(Action.Watch, ghostFlight);
        System.out.print(".\nНо ");

        //3 предложение
        ghost.getProperty("маленькое");
        ghost.getCoordinate(city);
        ghost.doSomething(Action.noDelete);
        System.out.print(".");

        //4 предложение
        ghost.getProperty(""); // возращаем обычное имя
        ghost.doSomething(Action.Spin);
        PlaceTime.setTime(Time.Night);
        System.out.println();
        ghost.getCoordinate(CoordinateEnum.All);
        System.out.print("и ");
        ghost.doSomething(Action.Kylbit);
        ghost.getCoordinate(sky);
        PlaceTime.setTime(Time.TimeByTime);
        System.out.print(".\nА ");

        //5 предложение
        sounds.getProperty("печальные");
        sounds.doSomething(Action.Talk);
        PlaceTime.setTime(Time.NotForAMoment);
        System.out.print(".");

        System.out.println("\n\n=================================================================================");
    }

}

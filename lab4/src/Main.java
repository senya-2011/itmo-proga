import Enums.Action;
import Enums.Gender;
import Enums.Place;
import Exceptions.AirDefenceException;
import Exceptions.DoNotShoutException;
import Objects.Entity;
import Objects.Thing;

public class Main {

    public static void main(String[] args) throws DoNotShoutException {

        //Создание "того" что может совершать действия
        Entity Karl = new Entity("Карласон", Gender.MALE){ //Анонимный класс
            protected void getEnding(Gender sex){//метод получения окончания
                if(Math.random()<0.1 && name == "Карласон"){ //Это надо только для Карлосона а для других персонажей нет
                    throw new AirDefenceException();         //Из-за этого создаем анонимный класс с переопределением
                }
                if(sex == Gender.FEMALE){
                    this.ending = "a ";
                }else if(sex == Gender.NEUTHER){
                    this.ending = "о ";
                }else if(sex == Gender.ALL){
                    this.ending = "и ";
                }else{
                    this.ending = " ";
                }
            }
        };
        Entity Baby =  new Entity("Малыш", Gender.MALE);
        Entity Chocolate = new Entity("брызги шоколада", Gender.ALL);
        Entity time =  new Entity("Времени", Gender.NEUTHER);
        Entity call =  new Entity("звонок", Gender.MALE);
        Entity Polu = new Entity("полы халата", Gender.ALL);

        //Создание бездейственных вещей (типа окно палка и тд)
        Thing all = new Thing("все");
        Thing moment = new Thing("момент");
        Thing dadya =  new Thing("дядя Юлиус");

        class Story{ //локальный класс
            void makeStory() throws DoNotShoutException {
                System.out.println("===============================\n\n\n");
                //1 предложение
                Karl.doSomething(Action.SNORT);
                Karl.getMessage(",");
                Chocolate.doSomething(Action.SPLASH, all);
                Chocolate.setPlace(Place.AROUND);
                Chocolate.getMessage(".");

                System.out.println();
                //2 предложение
                time.setProperty("для упражнений", Place.AFTER);
                time.setPlace(Place.CHECK);
                time.doSomething(Action.NOTENOUGH);
                time.getMessage(", потому что");
                moment.setProperty("именно в этот", Place.BEFORE);
                call.setProperty("долгий", Place.BEFORE);
                call.setPlace(Place.DOOR);
                call.doSomething(Action.SOUND);
                call.getMessage(".");

                System.out.println();
                //3 предложение
                Baby.doSomething(Action.THINK);
                Baby.getMessage(", что");
                dadya.setProperty("это", Place.BEFORE);
                Thing.EndSentence.endMessage(", и"); //использование вложенного статик класса, да тупо но больше негде было использьвать
                Baby.setProperty("со всех ног", Place.AFTER);
                Baby.doSomething(Action.RUN);
                Baby.doSomething(Action.OPEN);
                Baby.getMessage(".");

                System.out.println();
                //4 предложение
                Baby.setNewName("Он"); //использую метод смены имени
                Baby.setProperty("один очень", Place.AFTER);
                Baby.doSomething(Action.WANT);
                Baby.doSomething(Action.MEET, dadya);
                Baby.getMessage("--");
                Baby.doSomething(Action.THOUGHT);
                Baby.getMessage(", что");
                Karl.setProperty("спокойно", Place.AFTER);
                Karl.doSomething(Action.CAN);
                Karl.doSomething(Action.SLEEP);
                Karl.setPlace(Place.BED);
                Karl.getMessage(". Но");
                //нету переноса строки тк 1)след предложение короткое 2) иначе придеться сувать но в проверти Karl что не очень красиво
                //5 предложение
                Karl.setProperty("так не", Place.AFTER);
                Karl.doSomething(Action.THOUGHT);
                Karl.getMessage(".");

                System.out.println();
                //6 предложение
                Karl.setNewName("Он");
                Baby.setNewName("Малыш");
                Karl.doSomething(Action.STAY);
                Karl.setPlace(Place.BEHINDBABY);
                Karl.getMessage(", и");
                Polu.setProperty("купального", Place.AFTER);
                Polu.doSomething(Action.CONFUSE);
                Polu.setPlace(Place.INLEGS);
                Polu.getMessage(".");

                System.out.println("\n\n\n===============================");
            }
        }

        Story ourStory = new Story();
        ourStory.makeStory();
    }
}

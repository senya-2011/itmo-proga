package Objects;

import Enums.Action;
import Enums.Gender;
import Enums.Place;
import Exceptions.DoNotShoutException;
import Interfaces.IPlace;
import Interfaces.IProperty;

public class Entity extends Obj implements IPlace, IProperty{

    final private Gender sex;
    private String message = "";
    protected String ending; //окончание в предложениях


    public Entity(String name, Gender sex) throws DoNotShoutException {
        super(name);
        this.sex = sex;
    }
    class EndingByGender{ //вложенный класс
        static String getEnding(Gender sex){
            if(sex == Gender.FEMALE){
                return "a ";
            }else if(sex == Gender.NEUTHER){
                return "о ";
            }else if(sex == Gender.ALL){
                return "и ";
            }else{return " ";}
        }
    }


    protected void getEnding(Gender sex){//метод получения окончания
        ending = EndingByGender.getEnding(sex);
    }

    //метод смены имени одного и того же персонажа (Карласон -> Малыш)
    //чееее оказывается Малыш и Карлоасон это разные персонажи
    //специально не буду удалять этот кусок кода, зря чтоли делал
    public void setNewName(String name){
        super.name = name;
    }

    /*
    * Методы действий самому и на что-то(кого-то)
    * короче всякий мусор
    * */
    public void doSomething(Action act){
        getEnding(sex); //вызов метода получения окончания

        if(message != ""){
            message = message + " ";
        }else{this.message = this.message + super.name + " ";}

        switch (act){
            case SNORT:
                changeMessage("фыркнул");
                break;
            case SPLASH:
                changeMessage("забрызгал");
                break;
            case NOTENOUGH:
                changeMessage("не хватил");
                break;
            case SOUND:
                changeMessage("прозвучал");
                break;
            case THINK:
                changeMessage("сообразил");
                break;
            case RUN:
                changeMessage("побежал");
                break;
            case OPEN:
                changeMessage("открывать");
                break;
            case WANT:
                changeMessage("хотел");
                break;
            case MEET:
                changeMessage("встретить");
                break;
            case THOUGHT:
                changeMessage("считал");
                break;
            case CAN:
                changeMessage("может");
                break;
            case SLEEP:
                changeMessage("полежать");
                break;
            case STAY:
                changeMessage("стоял");
                break;
            case CONFUSE:
                changeMessage("путались");
                break;
        }
        this.message = this.message + ending;

    }
    public void doSomething(Action act, Obj obj){ //действие на кого-то(что-то)
        doSomething(act);
        this.message = this.message + obj.name+" ";
    }


    //метод для каких-либо изменений предложения
    private void changeMessage(String str){
        this.message = this.message + str;
    }


    //Самый важный метод вывод сообщения на экран без него мы бы не видили текст :(
    public void getMessage(String znak) throws DoNotShoutException {
        if(znak == "!"){
            throw new DoNotShoutException();
        }
        System.out.print(this.message + znak + " ");
        this.message = "";
    }

    @Override // переопределение интерфейса места
    public void setPlace(Place place){
        if(place == Place.AROUND){
            changeMessage("вокруг");
        }else if (place == Place.CHECK){
            changeMessage("в устном счете");
        }else if (place == Place.DOOR){
            changeMessage("в дверь");
        }else if (place == Place.BED){
            changeMessage("в постели");
        }else if (place == Place.BEHINDBABY){
            changeMessage("за спиной Малыша");
        }else if (place == Place.INLEGS){
            changeMessage("в ногах");
        }
    }
    @Override // переопределение интерфейса свойств
    public void setProperty(String str, Place place){
        if(place == Place.BEFORE){
            this.message = this.message + str + " " + name + " ";
        }else{ //понятное дело тут могут быть другие плейсы кроме AFTER, но проверку на это делать не стал тк зачем пользователь же ничего не вводит
            this.message = this.message + name+ " " +  str + " ";
        }

    }
}

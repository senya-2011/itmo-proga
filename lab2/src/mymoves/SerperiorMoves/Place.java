package mymoves.SerperiorMoves;

import ru.ifmo.se.pokemon.*;
import ru.ifmo.se.pokemon.Battle;

class Place{
    String message;
    protected Type Place(){
        double randNum = Math.random();
        String messageInChat = new String();

        Type place = Type.NORMAL;
        if (randNum<0.2){
            place = Type.ELECTRIC;
            messageInChat = "Началась гроза";
        }else if (randNum<0.4){
            place = Type.GROUND;
            messageInChat = "Началась песчаная буря";
        }else if (randNum<0.6){
            place = Type.FIRE;
            messageInChat = "Началось извержение вулкана";
        }else if (randNum<0.8){
            place = Type.WATER;
            messageInChat = "Началось цунами";
        }
        message = messageInChat;
        return place;
    }
}

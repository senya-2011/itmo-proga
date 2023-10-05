package mymoves.SerperiorMoves;

import ru.ifmo.se.pokemon.*;
import ru.ifmo.se.pokemon.Battle;

public class Place extends Camouflage {
    public Type Place(double randNum){
        Type place = Type.NORMAL;
        if (randNum<0.2){
            place = Type.ELECTRIC;
        }else if (randNum<0.4){
            place = Type.GROUND;
        }else if (randNum<0.6){
            place = Type.FIRE;
        }else if (randNum<0.8){
            place = Type.FIRE;
        }

        return place;
    }
    public String messageInChat(double randNum){
        String messageInChat = "Погода спокойна";

        if (randNum<0.2){
            messageInChat = "Началась гроза";
        }else if (randNum<0.4){
            messageInChat = "Битва проходит в пещере";
        }else if (randNum<0.6){
            messageInChat = "Началось извержение вулкана";
        }else if (randNum<0.8){
            messageInChat = "Началось извержение вулкана";
        }

        return messageInChat+ " тип покемона изменен исходя из погоды";
    }
}

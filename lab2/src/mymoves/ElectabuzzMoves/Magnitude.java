package mymoves.ElectabuzzMoves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class Magnitude extends PhysicalMove {
    public Magnitude(){
        super(Type.GROUND, 0, 100);
        double chance = Math.random()*100;
        double power;
        if (chance<=5){
            power =10;
        }else if (chance<=15){
            power=30;
        }else if (chance<=35){
            power=50;
        }else if (chance<=65){
            power=70;
        }else if (chance<=85){
            power=90;
        }else if (chance<=95){
            power=110;
        }else{
            power=150;
        }
        super.power = power;

    }
    @Override
    protected String describe(){
        return "прожимает Magnitude";
    }
}

package mymoves.SerperiorMoves;
import ru.ifmo.se.pokemon.*;

public class Camouflage extends StatusMove {
    double randNum = Math.random();
    public Camouflage(){
        super(Type.NORMAL, 100, 100);
        super.type = Place.randPlace();
    }


    @Override
    protected String describe(){

        return "прожимает Camouflage. "+ Place.message+"\nтип покемона поменялся из-за погоды";
    }

}

package mymoves.SerperiorMoves;
import ru.ifmo.se.pokemon.*;

public class Camouflage extends StatusMove {
    double randNum = Math.random();
    Place RandomPlace = new Place();
    public Camouflage(){
        super(Type.NORMAL, 100, 100);
        super.type = RandomPlace.Place();
    }


    @Override
    protected String describe(){

        return "прожимает Camouflage. "+ RandomPlace.message+"\nтип покемона поменялся из-за погоды";
    }

}

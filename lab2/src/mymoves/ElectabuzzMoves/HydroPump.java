package mymoves.ElectabuzzMoves;

import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class HydroPump extends SpecialMove {
    public HydroPump(){
        super(Type.WATER, 120, 80);
    }

    @Override
    protected String describe(){
        return "прожимает Hydro Pump";
    }

}

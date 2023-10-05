package mymoves.CansoonMove;

import mymoves.ElectabuzzMoves.ThunderShock;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class ThunderWave extends SpecialMove {
    public ThunderWave(){
        super(Type.ELECTRIC, 0, 90);
    }
    @Override
    protected String describe(){
        return "прожимает Thunder Wave";
    }

    @Override
    protected void applyOppEffects(Pokemon p){

        Effect.paralyze(p);
    }

}

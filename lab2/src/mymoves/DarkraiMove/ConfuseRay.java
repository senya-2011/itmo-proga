package mymoves.DarkraiMove;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

import java.util.concurrent.locks.Condition;

public class ConfuseRay extends StatusMove {
    public ConfuseRay(){
        super(Type.GHOST, 0, 100);
    }
    @Override
    protected String describe(){
        return "прожимает Confuse Ray";
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        Effect.confuse(p);
    }
}

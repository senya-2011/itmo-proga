package mymoves.SerperiorMoves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class Growl extends StatusMove {
    public Growl(double pow, double acc){

        super(Type.NORMAL, pow, acc);
    }

    @Override
    protected String describe(){

        return "прожимает Growl";
    }

    @Override
    protected void applyOppEffects(Pokemon p){

        p.setMod(Stat.ATTACK,-1);
    }
}

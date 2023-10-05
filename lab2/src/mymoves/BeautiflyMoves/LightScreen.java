package mymoves.BeautiflyMoves;

import ru.ifmo.se.pokemon.*;

public class LightScreen extends StatusMove {
    public LightScreen(){
        super(Type.PSYCHIC, 0, 100);
    }
    @Override
    protected String describe(){
        return "прожимает Light Screen";
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        Effect e = new Effect();
        p.setMod(Stat.ATTACK, -(int)(p.getStat(Stat.ATTACK)/2));
    }
}

package mymoves.ElectabuzzMoves;

import ru.ifmo.se.pokemon.*;

public class ThunderShock extends SpecialMove {
    public ThunderShock(){
        super(Type.ELECTRIC, 40, 100);
    }
    @Override
    protected String describe(){
        return "прожимает Thunder Shock";
    }

    @Override
    protected void applyOppEffects(Pokemon p){
        if (Math.random()<0.1){
            Effect.paralyze(p);
        }
    }
}

package mymoves.SerperiorMoves;
import ru.ifmo.se.pokemon.*;

public class SweetScent extends SpecialMove{
    public SweetScent(double pow, double acc){
        super(Type.NORMAL, pow, acc);
    }

    @Override
    protected String describe(){
        return "прожимает Sweet Scent";
    }

    @Override
    protected void applyOppEffects(Pokemon p){
        p.setMod(Stat.EVASION,-1);
    }
}

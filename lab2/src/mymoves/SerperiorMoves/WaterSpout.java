package mymoves.SerperiorMoves;
import ru.ifmo.se.pokemon.*;

public class WaterSpout extends SpecialMove{
    public WaterSpout(double maxHP){
        super(Type.WATER, 0, 100);
        super.power = power;
    }

    @Override
    protected String describe(){
        return "прожимает Water Spout";
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        double maxHP = p.getStat(Stat.HP);
        double power = 150*p.getHP()/maxHP;
        super.power = power;
        System.out.println(super.power);
    }
}

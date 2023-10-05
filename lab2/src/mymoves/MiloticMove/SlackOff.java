package mymoves.MiloticMove;

import mypokemon.Milotic;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class SlackOff extends StatusMove {
    public SlackOff(){
        super(Type.NORMAL, 0, 100);
    }

    @Override
    protected String describe(){
        return "прожимает Slack Off";
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        double currentHP = p.getHP();
        double maxHP = p.getStat(Stat.HP);
        double newHP = currentHP + maxHP/2;
        if (newHP+currentHP>maxHP){
            newHP = maxHP-currentHP;
        }
        newHP = -newHP;
        p.setMod(Stat.HP, (int)newHP);
    }

}

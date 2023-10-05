package mypokemon;

import mymoves.BeautiflyMoves.Blizzard;
import mymoves.BeautiflyMoves.LightScreen;
import mymoves.DarkraiMove.ConfuseRay;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Darkrai extends ConfuseRay {
    final double hp = 70;
    final double attack = 90;
    final double defense = 90;
    final double spAtk = 135;
    final double spDef = 90;
    final double speed = 125;
    public Darkrai(String name, int level){
        super(name, level);
        super.setType(Type.DARK);
        super.setStats(hp, attack, defense, spAtk, spDef, speed);
        super.setMove(new Blizzard(), new LightScreen(), new ConfuseRay());
    }
}

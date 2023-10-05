package mypokemon;

import mymoves.BeautiflyMoves.Blizzard;
import mymoves.BeautiflyMoves.LightScreen;
import mymoves.CansoonMove.ThunderWave;
import mymoves.DarkraiMove.ConfuseRay;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Cascoon extends ConfuseRay {
    final double hp = 50;
    final double attack = 35;
    final double defense = 55;
    final double spAtk = 25;
    final double spDef = 25;
    final double speed = 15;
    public Cascoon(String name, int level){
        super(name, level);
        super.setType(Type.BUG);
        super.setStats(hp, attack, defense, spAtk, spDef, speed);

        super.setMove(new Blizzard(), new LightScreen(), new ConfuseRay(),new ThunderWave());
    }
}

package mypokemon;

import mymoves.BeautiflyMoves.Blizzard;
import mymoves.BeautiflyMoves.LightScreen;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Beautifly extends LightScreen {

    final double hp = 60;
    final double attack = 70;
    final double defense = 50;
    final double spAtk = 100;
    final double spDef = 50;
    final double speed = 65;

    public Beautifly(String name, int level){
        super(name, level);
        super.setType(Type.BUG, Type.FLYING);
        super.setStats(hp, attack, defense, spAtk, spDef, speed);
        super.setMove(new Blizzard(), new LightScreen());
    }

}

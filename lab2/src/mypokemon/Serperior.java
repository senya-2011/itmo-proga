package mypokemon;

import mymoves.SerperiorMoves.Camouflage;
import mymoves.SerperiorMoves.Growl;
import mymoves.SerperiorMoves.SweetScent;
import mymoves.SerperiorMoves.WaterSpout;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Serperior extends Pokemon {
    final double hp = 75;
    final double attack = 75;
    final double defense = 95;
    final double spAtk = 75;
    final double spDef = 95;
    final double speed = 113;
    public Serperior(String name, int level){
        super(name, level);
        super.setType(Type.GRASS);

        //назначяем хар-ки покемона

        super.setStats(hp, attack, defense, spAtk, spDef, speed);

        super.setMove(new WaterSpout(hp), new Growl(0, 100), new SweetScent(0, 100), new Camouflage());
    }
}

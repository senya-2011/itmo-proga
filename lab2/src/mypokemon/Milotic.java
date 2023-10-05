package mypokemon;

import mymoves.ElectabuzzMoves.HydroPump;
import mymoves.ElectabuzzMoves.Magnitude;
import mymoves.ElectabuzzMoves.ThunderShock;
import mymoves.MiloticMove.SlackOff;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Milotic extends SlackOff {
    final double hp = 95;
    final double attack = 60;
    final double defense = 79;
    final double spAtk = 100;
    final double spDef = 125;
    final double speed = 81;

    public Milotic(String name, int level){
        super(name, level);
        super.setType(Type.WATER);
        super.setStats(hp, attack, defense, spAtk, spDef, speed);

        super.setMove(new HydroPump(), new Magnitude(), new ThunderShock(), new SlackOff());

    }
}

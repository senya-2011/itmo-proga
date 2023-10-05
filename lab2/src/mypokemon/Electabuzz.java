package mypokemon;

import mymoves.CansoonMove.ThunderWave;
import mymoves.ElectabuzzMoves.HydroPump;
import mymoves.ElectabuzzMoves.Magnitude;
import mymoves.ElectabuzzMoves.ThunderShock;
import mymoves.SerperiorMoves.Camouflage;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Electabuzz extends ThunderShock {
    final double hp = 65;
    final double attack = 83;
    final double defense = 57;
    final double spAtk = 95;
    final double spDef = 85;
    final double speed = 105;
    public Electabuzz(String name, int level){
        super(name, level);
        super.setType(Type.ELECTRIC);

        //назначяем хар-ки покемона

        super.setStats(hp, attack, defense, spAtk, spDef, speed);

        super.setMove(new HydroPump(), new Magnitude(), new ThunderShock());
    }
}

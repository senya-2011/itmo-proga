
import mypokemon.*;
import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Type;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();

        Beautifly p1 = new Beautifly("Бабочка", 1);
        Cascoon p2 = new Cascoon("Пиявка", 1);
        Milotic p3 = new Milotic("Змея", 1);
        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);

        Darkrai p4 = new Darkrai("Робот", 1);
        Electabuzz p5 = new Electabuzz("Електрик", 1);
        Serperior p6 = new Serperior("Русслака", 1);
        b.addFoe(p6);
        b.addFoe(p5);
        b.addFoe(p4);

        b.go();
    }
}

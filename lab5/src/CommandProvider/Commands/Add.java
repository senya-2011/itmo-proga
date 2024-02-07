package CommandProvider.Commands;

import SpaceMarines.SpaceMarine;

import java.util.Vector;

public class Add {
    public static void AddCommand(Vector collection){
        SpaceMarine sp = new SpaceMarine();
        collection.add(sp);
    }
}

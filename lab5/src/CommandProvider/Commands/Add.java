package CommandProvider.Commands;

import SpaceMarines.SpaceMarine;

import java.util.Vector;

public class Add {

    /**
     * add a new element to the collection
     *
     * @param collection collection where we store SpaceMarine objects
     * @see SpaceMarine
     * */
    public static void AddCommand(Vector collection){
        SpaceMarine sp = new SpaceMarine();
        collection.add(sp);
    }
}

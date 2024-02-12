package CommandProvider.Commands;

import CommandProvider.CompareSpaceMarine;
import SpaceMarines.SpaceMarine;

import java.util.Collections;
import java.util.Vector;

public class Add {

    /**
     * add a new element to the collection
     *
     * @param collection collection where we store SpaceMarine objects
     * @param CSM Comparator to sort
     * */
    public static void AddCommand(Vector collection, CompareSpaceMarine CSM){
        SpaceMarine sp = new SpaceMarine();
        collection.add(sp);
        Collections.sort(collection, CSM);
    }
}

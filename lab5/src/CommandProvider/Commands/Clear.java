package CommandProvider.Commands;

import java.util.Vector;

public class Clear {

    /**
     * clear collection
     *
     * @param collection collection where we store SpaceMarine objects
     * */
    public static void ClearCommand(Vector collection){
        collection.clear();
        System.out.println("Коллекция очищена.");
    }
}

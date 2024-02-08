package CommandProvider.Commands;

import java.util.Vector;

public class Show {
    /**
     * display all the elements
     * <p>
     * display to standard output all the elements of the collection in string representation
     *
     * @param collection collection where we store SpaceMarine objects
     * */
    public static void ShowCommand(Vector collection){
        for (Object o : collection) {
            System.out.println(o);
            System.out.println();
        }
    }
}

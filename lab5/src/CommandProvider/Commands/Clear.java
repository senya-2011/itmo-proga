package CommandProvider.Commands;

import java.util.Vector;

public class Clear {
    public static void ClearCommand(Vector collection){
        collection.clear();
        System.out.println("Коллекция очищена.");
    }
}

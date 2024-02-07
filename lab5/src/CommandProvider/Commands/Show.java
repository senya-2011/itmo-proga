package CommandProvider.Commands;

import java.util.Vector;

public class Show {
    public static void ShowCommand(Vector collection){
        for (Object o : collection) {
            System.out.println(o);
            System.out.println();
        }
    }
}

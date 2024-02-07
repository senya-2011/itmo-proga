package CommandProvider.Commands;

import java.util.LinkedList;

public class History {
    public static void HistoryCommand(LinkedList list){
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}

package CommandProvider.Commands;

import SpaceMarines.SpaceMarine;

import java.util.Vector;

import static SpaceMarines.JustScanner.Scan;

public class Save {

    /**
     * save the collection to a file
     *
     * @param collection collection where we store SpaceMarine objects
     * @return name of the file to save
     * */

    public static String SaveCommand(Vector<SpaceMarine> collection){
        System.out.println("Напише файл в который надо сохранить: ");
        String fileName = Scan();
        fileName.replaceAll(" ", "");
        fileName.replaceAll(".", "");
        if (fileName==""){
            return "data.csv";
        }
        return fileName+".csv";
    }
}

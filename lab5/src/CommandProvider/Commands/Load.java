package CommandProvider.Commands;

import Data.DataProvider;
import SpaceMarines.SpaceMarine;

import java.util.Vector;

import static SpaceMarines.JustScanner.Scan;

public class Load {
    /**
     * load file
     *
     * @param dp Data Class that will read the file
     * @return collection where we store SpaceMarine objects from file
     * */
    public static Vector<SpaceMarine> LoadCommand(DataProvider dp){
        System.out.print("Введите имя файла, который хотите загрузить: ");
        return dp.Load(Scan());
    }
}

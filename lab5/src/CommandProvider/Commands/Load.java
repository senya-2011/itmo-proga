package CommandProvider.Commands;

import Data.DataProvider;
import SpaceMarines.SpaceMarine;

import java.util.Vector;

import static SpaceMarines.JustScanner.Scan;

public class Load {
    public static Vector<SpaceMarine> LoadCommand(DataProvider dp){
        System.out.print("Введите имя файла, который хотите загрузить: ");
        return dp.Load(Scan());
    }
}

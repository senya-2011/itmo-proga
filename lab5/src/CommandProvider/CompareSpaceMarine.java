package CommandProvider;

import SpaceMarines.SpaceMarine;

import java.util.Comparator;

/**
 * This is class to Compare SpaceMarine Objects.
 * */
public class CompareSpaceMarine implements Comparator<SpaceMarine> {

    @Override
    public int compare(SpaceMarine sp1, SpaceMarine sp2){
        return sp1.getName().length() - sp2.getName().length();
    }
}

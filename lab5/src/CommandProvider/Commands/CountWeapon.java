package CommandProvider.Commands;

import SpaceMarines.SpaceMarine;
import SpaceMarines.Weapon;

import java.util.Vector;

public class CountWeapon {

    /**
     * display the number of elements greater than specified
     * <p>
     * display the number of elements whose weaponType field value is greater than the specified one
     *
     * @param collection collection where we store SpaceMarine objects
     * @param userString command argument meaning weapon value
     * */
    public static void CountWeaponCommand(Vector<SpaceMarine> collection, String userString){
        try{
            int userValue = Integer.parseInt(userString);
            int count = 0;

            for(SpaceMarine sp: collection){
                int value=0;
                Weapon weapon = sp.getWeaponType();
                switch (weapon){
                    case PLASMA_GUN:
                        value=0;
                        break;
                    case MULTI_MELTA:
                        value=1;
                        break;
                    case INFERNO_PISTOL:
                        value=2;
                        break;
                    case GRENADE_LAUNCHER:
                        value=3;
                        break;
                    }
                if(value>userValue){
                    count++;}

            }
            System.out.println("Кол-во элементов, значение поля weaponType которых больше заданного: "+count);


        }catch (NumberFormatException e){
            System.out.println("Значение должно быть weaponType int!");
        }
    }
}

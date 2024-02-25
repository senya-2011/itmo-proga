package main.Commands;

import interfaces.Command;
import main.Managers.CollectionManager;
import main.SpaceMarines.SpaceMarine;
import main.SpaceMarines.Weapon;

public class CountWeaponCommand implements Command {
    private CollectionManager cm;
    private int count;

    public CountWeaponCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {
        int userValue = Integer.parseInt(cm.getArg());
        int count = 0;
        int value = 0;
        Weapon weapon;

        for(SpaceMarine sp: cm.getCollection()){
            weapon = sp.getWeaponType();
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
        this.count = count;
    }

    @Override
    public String descr() {
        return "Кол-во элементов, значение поля weaponType которых больше заданного: "+count;
    }
}

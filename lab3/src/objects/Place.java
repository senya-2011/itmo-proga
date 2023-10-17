package objects;

import Enums.CoordinateEnum;
import interfaces.Coordinate;

import java.sql.Time;
import java.util.Objects;

public class Place extends Obj implements Coordinate {
    final String name;

    public Place(String name){
        super(name+"e");
        this.name = name+"e";
    }
    @Override
    public void getCoordinate(CoordinateEnum xyz){
        if (xyz == CoordinateEnum.NoCoordinate){
            super.name = super.name;
        }

    }
    @Override
    public void getCoordinate(Place place){
        super.name = super.name + " в " + place.name;
    }

}

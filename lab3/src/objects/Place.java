package objects;

import Enums.CoordinateEnum;
import interfaces.Coordinate;

import java.sql.Time;
import java.util.Objects;

public class Place extends Obj implements Coordinate {
    final String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(name, place.name);
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

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

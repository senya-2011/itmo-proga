package interfaces;

import Enums.CoordinateEnum;
import objects.Place;

public interface Coordinate {
    public void getCoordinate(CoordinateEnum xyz);//xyz - тк Ох Оу Оz
    public void getCoordinate(Place place);
}

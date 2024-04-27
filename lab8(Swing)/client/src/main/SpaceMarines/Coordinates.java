package main.SpaceMarines;

import java.io.Serializable;

import static main.SpaceMarines.JustScanner.Scan;

public class Coordinates implements Serializable {
    private static final long serialVersionUID = 2L;
    private int x;
    private Long y; //Поле не может быть null
    public Coordinates(String x, String y){
        this.x = Integer.parseInt(x);
        this.y = Long.parseLong(y);
    }
    @Override
    public String toString(){
        return ("x: "+String.valueOf(x)+", y: "+String.valueOf(y));
    }
}

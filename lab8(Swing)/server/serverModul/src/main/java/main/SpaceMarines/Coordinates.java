package main.SpaceMarines;

import java.io.Serializable;

import static main.SpaceMarines.JustScanner.Scan;

public class Coordinates implements Serializable {
    private static final long serialVersionUID = 2L;
    private int x;
    private Long y; //Поле не может быть null
    public Coordinates(int x, Long y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public Long getY(){
        return y;
    }

    @Override
    public String toString(){
        return ("x: "+String.valueOf(x)+", y: "+String.valueOf(y));
    }
}

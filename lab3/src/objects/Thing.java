package objects;

import interfaces.Property;

import java.util.Objects;

public class Thing extends Obj implements Property {
    final String name;

    public Thing(String name){
        super(name);
        this.name = name;
    }
    @Override
    public void getProperty(String property){
        super.name = this.name;
        super.name = property + " " + super.name;
    }
}

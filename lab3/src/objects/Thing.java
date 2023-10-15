package objects;

import interfaces.Property;

import java.util.Objects;

public class Thing extends Obj implements Property {
    final String name;

    @Override
    public String toString() {
        return "Thing{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Thing thing = (Thing) o;
        return Objects.equals(name, thing.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

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

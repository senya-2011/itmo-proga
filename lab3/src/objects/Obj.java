package objects;

import Enums.Action;
import Enums.Gender;

import java.util.Objects;

public abstract class Obj {
    String name;
    public Obj(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Obj obj = (Obj) o;
        return Objects.equals(name, obj.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Obj{}";
    }
}

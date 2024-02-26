package main.Managers;

import main.Data.DataProvider;
import main.SpaceMarines.SpaceMarine;

import java.util.Collections;
import java.util.Vector;

public class CollectionManager {

    ComparatorCollection comparator = new ComparatorCollection();
    DataProvider dp = new DataProvider();
    Vector<SpaceMarine> collection = dp.Load("data.csv");
    String arg;
    SpaceMarine sp;

    public void setSpaceMarine(SpaceMarine sp){
        this.sp = sp;
    }

    public void addCollection(){
        collection.add(sp);
        Collections.sort(collection, comparator);
        sp = null;
    }
    public Vector<SpaceMarine> getCollection(){
        Collections.sort(collection, comparator);
        return collection;
    }
    public void saveCollection(){
        dp.Save(collection, "data.csv");
    }
    public void clearCollection(){
        collection = new Vector<SpaceMarine>();
    }
    public SpaceMarine getSp() {
        return sp;
    }
    public void setArg(String s){
        arg = s;
    }
    public String getArg(){
        return arg;
    }
    public void setCollection(Vector<SpaceMarine> collection){
        this.collection = collection;
    }

}

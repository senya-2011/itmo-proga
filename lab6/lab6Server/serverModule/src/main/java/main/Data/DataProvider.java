package main.Data;

import main.SpaceMarines.SpaceMarine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;


/**
 * main.Data Class responsible for saving and unloading files
 * */
public class DataProvider {


    /**
     * Saving a collection to a file
     *
     * @param vector collection where we store SpaceMarine objects
     * @param fileName file name where to save
     * */
    public void Save(Vector<SpaceMarine> vector, String fileName){

        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(vector);
        } catch (Exception e) {
            System.out.println("Нет доступа к файлу");
        }
    }
    /**
     * Load a collection from file
     *
     * @param fileName the name of the file from which the collection should be loaded
     * @return collection where we store SpaceMarine objects
     * */
    public Vector<SpaceMarine> Load(String fileName){
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
            Vector<SpaceMarine> loadVector = (Vector<SpaceMarine>) inputStream.readObject();
            return loadVector;
        } catch (Exception e) {
            return new Vector<SpaceMarine>();
        }
    }
}

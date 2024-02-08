package Data;

import SpaceMarines.SpaceMarine;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;


/**
 * Data Class responsible for saving and unloading files
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
        System.out.println(fileName);
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
            Vector<SpaceMarine> loadVector = (Vector<SpaceMarine>) inputStream.readObject();
            System.out.println("Успешно загружено!");
            return loadVector;
        } catch (Exception e) {
            System.out.println("Файл не найден или в файле не Vector<SpaceMarine>!");
            return new Vector<SpaceMarine>();
        }
    }

    /**
     * Load a script from file
     *
     * @param fileName the name of the file from which the script should be loaded
     * @return collection where we store script
     * */
    public Vector<String> LoadScript(String fileName){
        Vector<String> lines = new Vector<String>();
        try{
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();//убираем пробелы
                lines.add(line);}
        }catch (FileNotFoundException e){
            System.out.println("К файлу нету доступа или он отсутствует!");
        }
        return lines;
    }

}

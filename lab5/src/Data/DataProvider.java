package Data;

import SpaceMarines.SpaceMarine;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class DataProvider {


    public void Save(Vector<SpaceMarine> vector, String fileName){

        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(vector);
        } catch (Exception e) {
            System.out.println("Нет доступа к файлу");
        }

    }
    public Vector<SpaceMarine> Load(String fileName){
        System.out.println(fileName);
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
            Vector<SpaceMarine> loadVector = (Vector<SpaceMarine>) inputStream.readObject();
            System.out.println("Успешно загружено!");
            return loadVector;
        } catch (Exception e) {
            System.out.println("Файл не найден!");
            return new Vector<SpaceMarine>();
        }
    }

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




        public static void UpdateCollection(Vector collection){
        System.out.println();
    }
}

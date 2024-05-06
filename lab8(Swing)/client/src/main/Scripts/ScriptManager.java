package main.Scripts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class ScriptManager {
    public static File file;
    public Vector<String> Script(String filename){
        HashMap<String, Integer> filenames= new HashMap<>();
        Vector<String> output = new Vector<>();
        Vector<String> loadS;


        filenames.put(filename, 1);
        Vector<String> strings = new Vector<>();
        loadS = LoadScript(filename);

        while(true){
            if(loadS.size()==0){
                return output;
            }
            strings.addAll(loadS);
            loadS.clear();
            for(String s: strings){
                output.add(s);
                if (s.split(" ")[0].equals("execute_script")){
                    output.remove(s);
                    if(filenames.containsKey(s.split(" ")[1])){
                        System.out.println("Рекурсия в скриптах!");
                        return null;
                    }else{
                        filenames.put(s.split(" ")[1], 1);
                    }

                    loadS = LoadScript(s.split(" ")[1]);
                }
            }
            strings.clear();
        }

    }

    private Vector<String> LoadScript(String fileName){
        Vector<String> lines = new Vector<String>();
        try{
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();//убираем пробелы
                if(!line.equals("")){
                    lines.add(line);
                }
                }
        }catch (FileNotFoundException e){
            System.out.println("К файлу нету доступа или он отсутствует!");
        }
        return lines;
    }
}

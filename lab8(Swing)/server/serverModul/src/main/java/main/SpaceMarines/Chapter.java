package main.SpaceMarines;

import java.io.Serializable;

import static main.SpaceMarines.JustScanner.Scan;


public class Chapter implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String parentLegion;
    private Integer marinesCount; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 1000
    public Chapter(String name, String parentLegion, Integer marinesCount){
        this.name = name;
        this.parentLegion = parentLegion;
        this.marinesCount = marinesCount;
    }

    public String getName() {
        return name;
    }

    public String getParentLegion() {
        return parentLegion;
    }
    public int getMarinesCount(){
        return marinesCount;
    }
    @Override
    public String toString(){
        return("\n\tname: "+name+"\n\tparentLegion: "+parentLegion+"\n\tmarinesCount: "+marinesCount);
    }
}
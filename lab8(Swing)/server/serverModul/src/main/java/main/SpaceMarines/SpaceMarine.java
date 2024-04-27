package main.SpaceMarines;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This is the collection class that we collect in this program.
 * */

public class SpaceMarine implements Serializable{ //разбор на байты и сбор обратно
    private static final long serialVersionUID = 3L;
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    //private java.time.LocalDate creationDateWithoutTime = LocalDate.now(); // Тоже самое что и выше но без время

    private Long health; //Поле может быть null, Значение поля должно быть больше 0
    private Long heartCount; //Поле не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 3
    private Weapon weaponType; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле может быть null
    private String user;

    public SpaceMarine(int id, String name, int x, Long y, LocalDateTime creationDate, Long health, Long heartCount, String weaponType, String meleeWeapon,
                       String chapterName, String parentLegion, Integer marinesCount) {
        this.id = id;
        this.name = name;
        this.coordinates = new Coordinates(x, y);
        this.creationDate = creationDate;
        this.health = health;
        this.heartCount = heartCount;
        this.weaponType = getWeapon(weaponType);
        this.meleeWeapon = getMeleeWeapon(meleeWeapon);
        if(chapterName==null){
            this.chapter=null;
        }else{
            this.chapter = new Chapter(chapterName, parentLegion, marinesCount);
        }
    }
    public MeleeWeapon getMeleeWeapon(String s){
        if (s!=null){
            switch (s){
                case "POWER_SWORD": return MeleeWeapon.POWER_SWORD;
                case "MANREAPER": return MeleeWeapon.MANREAPER;
                case "POWER_FIST": return MeleeWeapon.POWER_FIST;
            }
        }
        return null;
    }
    public Weapon getWeapon(String s){
        if(s!=null) {
            switch (s) {
                case "PLASMA_GUN":
                    return Weapon.PLASMA_GUN;
                case "GRENADE_LAUNCHER":
                    return Weapon.GRENADE_LAUNCHER;
                case "INFERNO_PISTOL":
                    return Weapon.INFERNO_PISTOL;
                case "MULTI_MELTA":
                    return Weapon.MULTI_MELTA;
            }
        }
        return null;
    }
    public String getChapterName(){
        if(chapter!=null){
            return chapter.getName();
        }
        return null;
    }
    public String getChapterLegion(){
        if(chapter!=null){
            return chapter.getParentLegion();
        }
        return null;
    }
    public int getChapterMarines(){
        if(chapter!=null){
            return chapter.getMarinesCount();
        }
        return 0;
    }
    public String getWeapon(){
        if(this.weaponType!=null){
            return weaponType.toString();
        }
        return null;
    }
    public String getMWeapon(){
        if(this.meleeWeapon!=null){
            return meleeWeapon.toString();
        }
        return null;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }


    public LocalDateTime getCreationDate() {
        return creationDate;
    }


    public Long getHealth() {
        return health;
    }


    public Long getHeartCount() {
        return heartCount;
    }

    public void setUser(String user){
        this.user = user;
    }
    public String getUser(){
        return user;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }


    public void setCreationDate(){
        this.creationDate = LocalDateTime.now();
    }


    public void setId(int newId){
        if(id==0){
            this.id = newId;
        }
        //только целая часть + 1 тк > 0
    }
    //пришлость сделать паблик тк взял за значение и его надо менять

    public int getId(){
        return id;
    }
    public Chapter getChapter(){return chapter; }
    public Weapon getWeaponType(){return weaponType;}
    public LocalDateTime getDate(){return creationDate;}

    public String getName(){return name;}

    @Override
    public String toString(){
        return ("Name: "+name+ "\nid: "+ id + "\nКоординаты: "+ coordinates+"\nДата: "+ creationDate+"\nhealth: "+ health+", heartCount: "+heartCount +
                "\nweaponType: "+ weaponType + "\nmeleeWeapon: "+meleeWeapon+"\nChapter: "+ chapter);
    }
}


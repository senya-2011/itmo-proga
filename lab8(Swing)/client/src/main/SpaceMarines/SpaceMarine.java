package main.SpaceMarines;

import java.io.Serializable;
import java.time.LocalDateTime;

import static main.SpaceMarines.JustScanner.Scan;

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

    public SpaceMarine(String name, String x, String y, String health, String heartCount, String weaponType, String meleeWeapon, String chapterName, String chapterLegion, String marines) {
        this.name = name;
        this.coordinates = new Coordinates(checkString(x), checkString(y));
        this.health = Long.parseLong(checkString(health));
        this.heartCount = Long.parseLong(checkString(heartCount));
        this.chapter = new Chapter(chapterName, chapterLegion, checkString(marines));
        setWeapon(weaponType);
        setMeleeWeapon(meleeWeapon);
        this.id = 0;
    }
    private String checkString(String s){
        s = s.replaceAll(",", "");
        if(s.equals("")){
            return "1";
        }else{
            return s;
        }
    }

    public String getUser(){
        return user;
    }
    private void setChapter(){
        int chapterChoice;
        while(true) {
            try {
                System.out.print("Если хотите добавить Chapter, введите 1 иначе 2: ");
                chapterChoice = Integer.parseInt(Scan());
                if (chapterChoice != 1 && chapterChoice != 2) {
                    System.out.println("Введите 1 или 2!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Надо ввести int!");
            }
        }
        switch (chapterChoice){
            case 1:
                //chapter = new Chapter();
                //chapter.makeChapter();
                break;
            case 2:
                break;
        }
    }
    private void setMeleeWeapon(String melee) {
        switch (melee) {
            case "POWER_SWORD":
                meleeWeapon = MeleeWeapon.POWER_SWORD;
                break;
            case "MANREAPER":
                meleeWeapon = MeleeWeapon.MANREAPER;
                break;
            case "POWER_FIST":
                meleeWeapon = MeleeWeapon.POWER_FIST;
                break;
        }

    }
    private void setHeartCount(){
        while (true) {
            System.out.print("Введите heartCount(0<heartCount<=3): ");
            try{
                heartCount = Long.parseLong(Scan());
                if (heartCount>3 || heartCount<=0){
                    System.out.println("Значение heartCount должно быть от 1 до 3!");
                }else{
                    break;
                }
            }catch(NumberFormatException e){
                System.out.println("Надо ввести Long");
            }

        }
    }

    private void setWeapon(String weapon){
            switch (weapon){
                case "PLASMA_GUN":
                    weaponType = Weapon.PLASMA_GUN;
                    break;
                case "GRENADE_LAUNCHER":
                    weaponType = Weapon.GRENADE_LAUNCHER;
                    break;
                case "INFERNO_PISTOL":
                    weaponType = Weapon.INFERNO_PISTOL;
                    break;
                case "MULTI_MELTA":
                    weaponType = Weapon.MULTI_MELTA;
                    break;
                case "null":
                    weaponType = null;
                    break;
            }
    }


    private void setHealth(){
        while(true){
            System.out.print("Введите значение больше 0 (Long)health: ");
            try{
                String sHealth = Scan();
                if (sHealth.replaceAll(" ", "").equals("")){
                    health = null;
                    break;
                }
                health = Long.parseLong(sHealth);
                if (health<=0){
                    System.out.println("Значение health должно быть больше 0!");
                }else{
                    break;
                }
            }catch(NumberFormatException e){
                System.out.println("Надо ввести Long!");
            }
        }
    }
    public void setId(int id){
        this.id = id;
    }
    //пришлость сделать паблик тк взял за значение и его надо менять
    private void setName(){
        while (true){
            System.out.print("Введите имя: ");
            name = Scan();
            if(name.replaceAll(" ", "").equals("")){
                System.out.println("Вы ввели пустую строчку! Имя не может быть пустой строчкой.");
            }else{break;}
        }
    }

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

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }
}


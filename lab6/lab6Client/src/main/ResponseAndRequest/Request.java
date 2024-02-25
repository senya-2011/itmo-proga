package main.ResponseAndRequest;

import main.SpaceMarines.SpaceMarine;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 228L;
    private String name;
    private String id;
    private SpaceMarine sp;


    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpaceMarine getSp() {
        return sp;
    }

    public void setSp(SpaceMarine sp) {
        this.sp = sp;
    }

}

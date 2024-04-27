package main.ResponseAndRequest;

import main.SpaceMarines.SpaceMarine;

import java.io.Serializable;
import java.util.Vector;

public class Response implements Serializable {
    private static final long serialVersionUID = 328L;
    private String respose;
    private String[][] data;
    private String[][] myData;
    public Response(String s){
        respose = s;
    }
    public String getRespose(){
        return respose;
    }
    public String[][] getData(){
        return data;
    }
    public String[][] getMyData(){
        return myData;
    }
}

package main.ResponseAndRequest;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 328L;
    private String respose;
    public Response(String s){
        respose = s;
    }
    public String getRespose(){
        return respose;
    }
}

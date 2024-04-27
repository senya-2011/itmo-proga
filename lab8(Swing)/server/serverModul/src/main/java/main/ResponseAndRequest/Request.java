package main.ResponseAndRequest;

import main.SpaceMarines.SpaceMarine;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 228L;
    private String name;
    private String id;
    private String login;
    private String password;
    public String getId(){
        return id;
    }
    private SpaceMarine sp;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

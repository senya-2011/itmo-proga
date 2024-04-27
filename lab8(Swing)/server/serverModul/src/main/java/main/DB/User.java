package main.DB;

import main.SpaceMarines.SpaceMarine;

import java.util.Vector;

public class User {
    String login;
    String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }


    @Override
    public String toString(){
        return "login: "+ login+ " pass: "+ password;
    }
}

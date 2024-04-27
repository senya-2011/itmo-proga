package main.Data.Commands;

import interfaces.Command;
import main.DB.User;
import main.Managers.CollectionManager;

import java.util.Vector;

public class LoginCommand implements Command {
    CollectionManager collectionManager;
    String login;
    String password;
    public LoginCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    String descr = null;
    @Override
    public void execute() {
        this.login = collectionManager.getLogin();
        this.password = collectionManager.getPassword();
        boolean status = true;
        for(User user: collectionManager.getUsers()){
            if(user.getLogin().equals(login) && user.getPassword().equals(password)){
                descr = "Вы успешно вошли";
                status = false;
            }else if(user.getLogin().equals(login) && !user.getPassword().equals(password)){
                descr = "Неправильный пароль";
                status = false;
            }

        }
        if(status){
            descr = "Вы зарегистрировали нового юзера";
            User user = new User(login, password);
            collectionManager.addUser(user);
        }
    }

    @Override
    public String descr() {
        return descr;
    }
}

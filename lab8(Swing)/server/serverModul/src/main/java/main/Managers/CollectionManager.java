package main.Managers;

import main.DB.User;
import main.Data.DataProvider;
import main.SpaceMarines.SpaceMarine;

import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CollectionManager {

    private final Lock lock;
    ComparatorCollection comparator = new ComparatorCollection();
    DataProvider dp = new DataProvider();
    Vector<SpaceMarine> collection = dp.Load();
    public static Vector<SpaceMarine> correctCollection;
    Vector<User> users = dp.LoadUsers();
    Vector<String> activeUsers = dp.getActiveUsers();
    String arg;
    SpaceMarine sp;
    public static String login;
    String password;

    public CollectionManager() {
        lock = new ReentrantLock();
        this.correctCollection = collection;
    }

    public void setSpaceMarine(SpaceMarine sp){
        this.sp = sp;
    }

    public void addCollection(){
        this.lock.lock();
        try{
            sp.setUser(login);
            collection.add(sp);
            Collections.sort(collection, comparator);
            sp = null;
        }finally {
            this.lock.unlock();
        }

    }
    public Vector<SpaceMarine> getCollection(){
        return collection;
    }
    public void saveCollection(){
        dp.Save(collection, activeUsers);
    }
    public void saveUsers(){dp.saveUsers(users);}

    public SpaceMarine getSp() {
        return sp;
    }
    public void setArg(String s){
        arg = s;
    }
    public String getArg(){
        return arg;
    }
    public void setCollection(Vector<SpaceMarine> collection){
        this.lock.lock();
        try{
            this.collection = collection;
            correctCollection = collection;
        }finally {
            this.lock.unlock();
        }
    }
    public void addUser(User user){
        this.lock.lock();
        try{
            users.add(user);
        }finally {
            this.lock.unlock();
        }
    }

    public Vector<User> getUsers() {
        return users;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.lock.lock();
        try{
            this.login = login;
        }finally {
            this.lock.unlock();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

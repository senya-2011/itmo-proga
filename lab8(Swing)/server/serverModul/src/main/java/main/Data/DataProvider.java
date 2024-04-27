package main.Data;

import main.DB.DbFunction;
import main.DB.User;
import main.SpaceMarines.SpaceMarine;

import java.sql.Connection;
import java.util.Vector;

import static main.DB.DbFunction.*;


/**
 * main.Data Class responsible for saving and unloading files
 * */
public class DataProvider {
    String dbName = "studs"; //lab7db studs
    String host = "localhost";
    int port = 5432; //2222 5432
    String user = "user"; //postgres s408138
    String password = "password"; //qwerty
    Connection connection = DbFunction.connect_to_db(dbName, host, port, user, password);
    //ssh -L 5432:pg:5432 -p 2222 s408138@se.ifmo.ru

    /**
     * Saving a collection to a file
     *
     * @param vector collection where we store SpaceMarine objects
     * */
    public void Save(Vector<SpaceMarine> vector, Vector<String> users){
        truncateTable(connection, "SpaceMarine");
        for(int i=0; i< vector.size(); i++){
            insertSpaceMarine(connection, vector.get(i), vector.get(i).getUser());
        }
    }
    /**
     * Load a collection from file
     *
     * @return collection where we store SpaceMarine objects
     * */
    public Vector<SpaceMarine> Load(){
        try{
            return selectSpaceMarineTable(connection);
        } catch (Exception e) {
            throw e;
        }
    }
    public Vector<User> LoadUsers(){
        return selectUsersTable(connection);
    }
    public void saveUsers(Vector<User> vector){
        dropTable(connection, "Users");
        createUsers(connection);
        for(User user: vector){
            insertUser(connection, user.getLogin(), user.getPassword());
        }
    }
    public Vector<String> getActiveUsers(){
        return selectActiveUsers(connection);
    }
}

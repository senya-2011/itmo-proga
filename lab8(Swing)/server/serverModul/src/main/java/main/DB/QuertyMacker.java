package main.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuertyMacker {

    public static String createUsersTable(){
        return String.format("CREATE TABLE %s(" +
                "id serial PRIMARY KEY, " +
                "login varchar(30), pass varchar(128)" +
                ");", "Users");
    }

    public static void insertUser(String name, String password, Connection connection){

        String query = "INSERT INTO Users (login, pass) VALUES(?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Устанавливаем параметры запроса
            statement.setString(1, name);
            statement.setString(2, password);
            // Выполняем запрос
            statement.executeUpdate();
            System.out.println("Data has been inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String selectTable(String tableName){
        return String.format("SELECT * FROM %s", tableName);
    }
    public static String updateID(String tableName, int oldID, int newID){
        return String.format("UPDATE %s SET id=%d WHERE id=%d", tableName, newID, oldID);
    }
    public static String updateName(String tableName, int id, String name){
        return String.format("UPDATE %s SET name='%s' WHERE id=%d", tableName,name, id);
    }
    public static String insertSpaceMarine(String name, byte[] bytes, String user){

        return ("INSERT INTO SpaceMarine (name, bytes, userName) VALUES('"+name+"', '"+bytes+"', '"+user+"'");
    }
    public static String deleteRow(String tableName, int id){
        return String.format("DELETE FROM %s WHERE id=%d", tableName, id);
    }
    public static String findUser(String tableName, String user){
        return String.format("SELECT COUNT(*) FROM %s WHERE name='%s'", tableName, user);
    }
    public static String getUserPassword(String tableName, String user){
        return String.format("SELECT password FROM %s WHERE name='%s'", tableName, user);
    }
}


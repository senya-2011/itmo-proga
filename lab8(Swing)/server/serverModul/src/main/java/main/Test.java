package main;

import com.jcraft.jsch.*;
import main.DB.DbFunction;

import java.sql.Connection;

import static main.DB.DbFunction.selectUsersTable;


public class Test {
    public synchronized static void main(String[] args) {
        String dbName = "studs";
        String host = "localhost";
        int port = 5432;
        String user = "s408138";
        String password = "";
        Connection connection = DbFunction.connect_to_db(dbName, host, port, user, password);
        selectUsersTable(connection);

        synchronized (connection){

        }
    }
}


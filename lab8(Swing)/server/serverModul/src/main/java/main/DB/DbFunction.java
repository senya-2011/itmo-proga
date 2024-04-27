package main.DB;

import main.SpaceMarines.SpaceMarine;

import java.sql.*;
import java.util.Vector;

public class DbFunction {

    public static Connection connect_to_db(String dbname, String host, int port, String user, String password) {
        String url = String.format("jdbc:postgresql://%s:%d/%s", host, port, dbname);
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("DataBase connection successful");
            return connection;
        } catch (Exception e) {
            System.out.println("Коннектимся к локальной дб. Гелиус пал");
            String dbName1 = "lab7db"; //lab7db studs
            String host1 = "localhost";
            int port1 = 2222; //2222 5432
            String user1 = "postgres"; //postgres s408138
            String password1 = "qwerty"; //qwerty
            return connect_to_db(dbName1, host1, port1, user1, password1);
        }

    }

    public static void truncateTable(Connection connection, String tableName) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE " + tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertUser(Connection connection, String name, String password) {
        QuertyMacker.insertUser(name, password, connection);
        System.out.println("Add User");
    }

    public static void insertSpaceMarine(Connection connection, SpaceMarine spaceMarine, String user) {
/*
* String name, int x, Long y, LocalDateTime creationDate, Long health, Long heartCount, String weaponType, String meleeWeapon,
                       String chapterName, String parentLegion, Integer marinesCount
*
* */
        String query = "INSERT INTO SpaceMarine (id ,name, x, y, creationDate, health, heartCount, weaponType, meleeWeapon, chapterName, parentLegion, marinesCount,userName) " +
                "VALUES (? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            // Устанавливаем параметры запроса
            statement.setInt(1, spaceMarine.getId());
            statement.setString(2, spaceMarine.getName());
            statement.setInt(3, spaceMarine.getCoordinates().getX());
            statement.setLong(4, spaceMarine.getCoordinates().getY());
            statement.setTimestamp(5, Timestamp.valueOf(spaceMarine.getCreationDate()));
            statement.setLong(6, spaceMarine.getHealth());
            statement.setLong(7, spaceMarine.getHeartCount());
            statement.setString(8, spaceMarine.getWeapon());
            statement.setString(9, spaceMarine.getMWeapon());
            statement.setString(10, spaceMarine.getChapterName());
            statement.setString(11, spaceMarine.getChapterLegion());
            statement.setInt(12, spaceMarine.getChapterMarines());
            statement.setString(13, user);

            // Выполняем запрос
            statement.executeUpdate();

            System.out.println("Data has been inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Vector<User> selectUsersTable(Connection connection) {
        Vector<User> vector = new Vector<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QuertyMacker.selectTable("Users"));
            while (resultSet.next()) {
                User user = new User(resultSet.getString("login"), resultSet.getString("pass"));
                vector.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vector;
    }

    public static Vector<SpaceMarine> selectSpaceMarineTable(Connection connection) {
        Vector<SpaceMarine> vector = new Vector<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QuertyMacker.selectTable("SpaceMarine"));
            while (resultSet.next()) {
                SpaceMarine spaceMarine = new SpaceMarine(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("x"), resultSet.getLong("y"), resultSet.getTimestamp("creationDate").toLocalDateTime(),
                        resultSet.getLong("health"), resultSet.getLong("heartCount"), resultSet.getString("weaponType"),
                        resultSet.getString("meleeWeapon") ,resultSet.getString("chapterName"), resultSet.getString("parentLegion"),
                        resultSet.getInt("marinesCount"));
                spaceMarine.setUser(resultSet.getString("userName"));
                vector.add(spaceMarine);
            }
            return vector;
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public static Vector<String> selectActiveUsers(Connection connection) {
        Vector<String> vector = new Vector<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QuertyMacker.selectTable("SpaceMarine"));
            while (resultSet.next()) {
                vector.add(resultSet.getString("userName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vector;
    }
    public static void createUsers(Connection connection){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(QuertyMacker.createUsersTable());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void dropTable(Connection connection, String tableName){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE "+tableName);
            System.out.println("Delete Table");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


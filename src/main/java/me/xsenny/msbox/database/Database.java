package me.xsenny.msbox.database;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Database {

    private static Connection connection;
    private static Statement statement;

    public static void connect(){
        connection = null;
        try{
            File file = new File("plugins/MsBox/database.db");
            if (!file.exists()){
                file.createNewFile();
            }
            String url = "jdbc:sqlite:" + file.getPath();
            connection = DriverManager.getConnection(url);
            System.out.println("Baza de date creata");
            statement = connection.createStatement();
        }catch (SQLException | IOException e){
            System.out.println("EROARE");
        }
    }

    public static void disconnect(){
        try{
            if(connection != null) {
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void onUpdate(String sql){
        try{
            statement.execute(sql);
        }catch (SQLException e){
            System.out.println("EROARE DE EXECUTARE");
            System.out.println(e);
        }
    }

    public static ResultSet onQuery(String sql){
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

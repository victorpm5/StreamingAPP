package com.isdcm.streamingapp.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static Connection con;
    private static String url = "jdbc:sqlite:streaming.db";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url);

            Statement statement = con.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("create table if not exists User (id integer primary key, name string, surname string, email string, username string, password string)");
            statement.executeUpdate("create table if not exists Video (id real primary key, title string, autor string, fechaCreacion date, duracion integer, numReproducciones integer, descripcion string, formato string, url string)");

            return con;
    }

    public static void closeConnection() throws SQLException {
        con.close();
    }
}

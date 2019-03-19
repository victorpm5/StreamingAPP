package com.isdcm.streamingapp.services;

import com.isdcm.streamingapp.models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {

    public static boolean NewUser(User user){

        try {
            Connection conn = DBConnection.getConnection();

            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("insert into person (username, password) values('"+user.getUsername()+"','"+user.getPassword()+"')");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}

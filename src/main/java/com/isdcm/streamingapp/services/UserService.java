package com.isdcm.streamingapp.services;

import com.isdcm.streamingapp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    private static final String InsertUser = "INSERT INTO User(name,surname,email,username,password) VALUES (?, ?, ?, ?, ?)";
    private static final String CheckUserExisit = "SELECT * FROM User WHERE email= ? OR username= ?";
    private static final String CheckUserLogin = "SELECT * FROM User WHERE username= ?";

    private static final String UserEmailAlreadyExist = "Email altready on use";
    private static final String UserNameAlreadyExist = "Username not available";
    private static final String UserAvailable = "User available";

    private static final String BadPassword = "The provided password is not correct";
    private static final String Welcome = "Welcome";
    private static final String UserNotExist = "This username does not exist";

    public static String NewUser(User user) throws SQLException, ClassNotFoundException {

        String response = CheckUser(user);

        if(response.equals(UserAvailable)) InsertUser(user);

        return response;
    }

    public static String CheckLogin(String username, String password) throws ClassNotFoundException, SQLException {
        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement statement = conn.prepareStatement(CheckUserLogin);
            statement.setQueryTimeout(30);

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            String response = Welcome;

            if(result.next()){
                if(!result.getString("password").equals(password)) response = BadPassword;
            }
            else response = UserNotExist;

            return response;

        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    private static void InsertUser(User user) throws ClassNotFoundException, SQLException {

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement statement = conn.prepareStatement(InsertUser);
            statement.setQueryTimeout(30);

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());

            statement.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        }
        finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    private static String CheckUser(User user) throws ClassNotFoundException, SQLException {
        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement statement = conn.prepareStatement(CheckUserExisit);
            statement.setQueryTimeout(30);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());

            ResultSet result = statement.executeQuery();

            String response = UserAvailable;

            while(result.next() && response.equals(UserAvailable)){
                if(result.getString("email").equals(user.getEmail())) response = UserEmailAlreadyExist;
                else if(result.getString("username").equals(user.getEmail())) response = UserNameAlreadyExist;
            }

            return response;

        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                throw e;
            }
        }
    }

}

package com.isdcm.streamingapp.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "LoginServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher a = request.getRequestDispatcher("register.jsp");
        a.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String usuari = request.getParameter("usuari");
        String contrasenya = request.getParameter("contrasenya");
        String contrasenya2 = request.getParameter("contrasenya2");

        if(contrasenya!=contrasenya2){
            RequestDispatcher a = request.getRequestDispatcher("register.jsp?msg=Les contrasenyes no coincideixen");
            a.forward(request, response);
        }
        else {
            String msg = createUser(usuari, contrasenya);
            if(msg=="Nou usuari"){
                RequestDispatcher a = request.getRequestDispatcher("login");
                a.forward(request, response);
            }
            else{
                RequestDispatcher a = request.getRequestDispatcher("register.jsp?msg=Error");
                a.forward(request, response);
            }
        }
    }

    private String createUser(String usuari, String contrasenya){
        String url = "jdbc:sqlite:streaming.db";
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("delete table person");
            statement.executeUpdate("create table person (id integer primary key, username string, password string)");
            statement.executeUpdate("insert into person (username, password) values("+usuari+","+contrasenya+")");
            return "Nou usuari";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}

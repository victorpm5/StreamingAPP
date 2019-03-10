package com.isdcm.streamingapp.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher a = request.getRequestDispatcher("login.jsp");
		a.forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String usuari = request.getParameter("usuari");
		String contrasenya = request.getParameter("contrasenya");

		if (esValid(usuari, contrasenya)){
			RequestDispatcher a = request.getRequestDispatcher("login.jsp?msg=Benvingut");
			a.forward(request, response);
		} else {
			RequestDispatcher a = request.getRequestDispatcher("login.jsp?msg=Error");
			a.forward(request, response);
		}


	}


	//Mètode dummy, sempre és vàlid
	private Boolean esValid(String usuari, String contrasenya){
		//return true;
		String url = "jdbc:sqlite:streaming.db";
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(url);
			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate("create table person (id integer, name string)");
			statement.executeUpdate("insert into person values(1, 'leo')");
			statement.executeUpdate("insert into person values(2, 'yui')");

			ResultSet rs = statement.executeQuery("select * from person");
			while(rs.next())
			{
				// read the result set
				System.out.println("name = " + rs.getString("name"));
				System.out.println("id = " + rs.getInt("id"));
			}

            return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

}

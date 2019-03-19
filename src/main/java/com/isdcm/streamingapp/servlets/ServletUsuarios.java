package com.isdcm.streamingapp.servlets;

import com.isdcm.streamingapp.models.User;
import com.isdcm.streamingapp.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "ServletUsuarios", urlPatterns = "/login")
public class ServletUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher a = request.getRequestDispatcher("login.jsp");
		a.forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("login")!=null){
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
		else if (request.getParameter("register")!=null){
			response.sendRedirect("register.jsp?msg=Error");
		}

	}


	//Mètode dummy, sempre és vàlid
	private Boolean esValid(String usuari, String contrasenya){
		User user = new User("David", "Aleu","david.aleu.16@gmail.com", "aleueet","1111");
		UserService.NewUser(user);
		return true;
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

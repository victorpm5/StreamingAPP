package com.isdcm.streamingapp.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
		return true;
	}

}

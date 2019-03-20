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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "ServletUsuarios", urlPatterns = "/login")
public class ServletUsuarios extends HttpServlet {

	private static final long serialVersionUID = 1L;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
       


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher a = request.getRequestDispatcher("login.jsp");
		a.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("login")!=null){

			String username = request.getParameter("username");
			String password = request.getParameter("password");

            try {
                UserService.CheckLogin(username, password);
                response.sendRedirect("login.jsp?msg=WELCOME!!!!!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

		}
		else if (request.getParameter("register")!=null){

		    if(validPassword(request.getParameter("password"),request.getParameter("password2"))){

		        if(validEmail(request.getParameter("email"))){
                    User user = new User(
                            request.getParameter("name"),
                            request.getParameter("surname"),
                            request.getParameter("email"),
                            request.getParameter("username"),
                            request.getParameter("password")
                    );

                    try{
                        String result = UserService.NewUser(user);
                        response.sendRedirect("register.jsp?msg=Nou Usuari!!!!!");
                    }
                    catch(Exception e) {
                        response.sendRedirect("register.jsp?msg="+e.toString());
                    }
                }
		        else {
                    response.sendRedirect("register.jsp?msg=El correu electronic no esta en el format pertinent");
                }
            }
		    else {
                response.sendRedirect("register.jsp?msg=Les contrasenyes no coincideixen");
            }
		}

	}

	private Boolean validPassword(String password1, String password2){
	    return !password1.isEmpty() && password1.equals(password2);
    }

    private Boolean validEmail(String email){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

}

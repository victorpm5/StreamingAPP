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

import static com.isdcm.streamingapp.Utils.Constants.*;

@WebServlet(name = "ServletUsuarios", urlPatterns = "/login")
public class ServletUsuarios extends HttpServlet {

	private static final long serialVersionUID = 1L;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("login")!=null){

			String username = request.getParameter("username");
			String password = request.getParameter("password");

            try {
                String result = UserService.CheckLogin(username, password);
                if(result.equals(BadPassword) || result.equals(UserNotExist)){
                    response.sendRedirect("login.jsp?error=true&msg="+result);
                }
                else response.sendRedirect("listadoVid.jsp");
            } catch (ClassNotFoundException | SQLException e) {
                response.sendRedirect("login.jsp?error=true&msg="+e.getMessage());
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
                        if(result.equals(UserEmailAlreadyExist) || result.equals(UserNameAlreadyExist)) {
                            response.sendRedirect("registroUsu.jsp?error=true&msg="+result);
                        }
                        else response.sendRedirect("listadoVid.jsp");
                    }
                    catch(Exception e) {
                        response.sendRedirect("registroUsu.jsp?error=true&msg="+e.getMessage());
                    }
                }
		        else {
                    response.sendRedirect("registroUsu.jsp?error=true&msg="+EmailFormatNotCorrect);
                }
            }
		    else {
                response.sendRedirect("registroUsu.jsp?error=true&msg="+PasswordsNotEqual);
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

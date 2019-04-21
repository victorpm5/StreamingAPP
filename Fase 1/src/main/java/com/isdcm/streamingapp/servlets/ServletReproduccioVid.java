package com.isdcm.streamingapp.servlets;

import com.isdcm.streamingapp.services.ReproduccioRestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletReproduccioVid", urlPatterns = "/reproduccio")
public class ServletReproduccioVid extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        String url = request.getParameter("url");
        String titol = request.getParameter("titol");

        ReproduccioRestService.reprodueix(id);

        request.setAttribute("url", url.replace("watch?v=","embed/"));
        request.setAttribute("titol", titol);
        RequestDispatcher a = request.getRequestDispatcher("reproduccio.jsp");
        a.forward(request, response);
    }

}

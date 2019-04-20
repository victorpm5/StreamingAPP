package com.isdcm.streamingapp.servlets;

import com.isdcm.streamingapp.services.ReproduccioRestService;

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

        ReproduccioRestService.reprodueix(id);

        response.sendRedirect("listadoVid");
    }

}

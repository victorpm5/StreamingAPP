package com.isdcm.streamingapp.servlets;

import com.isdcm.streamingapp.models.Video;
import com.isdcm.streamingapp.services.VideoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletListadoVid", urlPatterns = "/listadoVid")
public class ServletListadoVid extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Video> videos = new ArrayList<Video>();

        try {
            videos = VideoService.GetVideos();
        } catch (SQLException | ClassNotFoundException e) {
            response.sendRedirect("listadoVid.jsp?error=true&msg="+e.getMessage());
        }

        request.setAttribute("videos", videos);
        RequestDispatcher a = request.getRequestDispatcher("listadoVid.jsp");
        a.forward(request, response);
    }

}

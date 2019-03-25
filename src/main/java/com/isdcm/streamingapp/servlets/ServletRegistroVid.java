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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ServletRegistroVid", urlPatterns = "/newvideo")
public class ServletRegistroVid extends HttpServlet {

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Video video = new Video(
                    request.getParameter("title"),
                    request.getParameter("autor"),
                    formatter.parse(request.getParameter("fechaCreacion")),
                    Float.parseFloat(request.getParameter("duracion")),
                    0,
                    request.getParameter("descripcion"),
                    request.getParameter("formato"),
                    request.getParameter("url")
            );

            VideoService.NewVideo(video);

        } catch (ParseException | SQLException | ClassNotFoundException e) {
            response.sendRedirect("registroVid.jsp?error=true&msg="+e.getMessage());
        }
    }

}

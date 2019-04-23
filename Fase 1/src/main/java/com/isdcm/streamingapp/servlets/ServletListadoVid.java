package com.isdcm.streamingapp.servlets;

import com.isdcm.streamingapp.services.soap.*;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletListadoVid", urlPatterns = "/listadoVid")
public class ServletListadoVid extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sessioUsuari= (HttpSession) request.getSession();
        String usuariLogged = (String) sessioUsuari.getAttribute("usuari");

        //Si entrem aquí és pq no hi ha sessió
        if(StringUtils.isBlank(usuariLogged)){
            RequestDispatcher a = request.getRequestDispatcher("login.jsp");
            a.forward(request, response);
        }

        VideosPortService portService = new VideosPortService();
        VideosPort port = portService.getVideosPortSoap11();
        List<Video> videos;

        if(request.getParameter("filter")!=null){
            if(request.getParameter("filter").equals("title")){
                FilterVideosByTitleRequest requestVideos = new FilterVideosByTitleRequest();
                requestVideos.setTitle(request.getParameter("value"));
                FilterVideosByTitleResponse responseVideos = port.filterVideosByTitle(requestVideos);
                videos = responseVideos.getVideo();
            }
            else if (request.getParameter("filter").equals("autor")){
                FilterVideosByAutorRequest requestVideos = new FilterVideosByAutorRequest();
                requestVideos.setAutor(request.getParameter("value"));
                FilterVideosByAutorResponse responseVideos = port.filterVideosByAutor(requestVideos);
                videos = responseVideos.getVideo();
            }
            else {
                FilterVideosByYearRequest requestVideos = new FilterVideosByYearRequest();
                requestVideos.setYear(Integer.parseInt(request.getParameter("value")));
                FilterVideosByYearResponse responseVideos = port.filterVideosByYear(requestVideos);
                videos = responseVideos.getVideo();
            }
        }
        else{
            GetVideosRequest requestVideos = new GetVideosRequest();
            GetVideosResponse responseVideos = port.getVideos(requestVideos);
            videos = responseVideos.getVideo();
        }

        request.setAttribute("videos", videos);
        RequestDispatcher a = request.getRequestDispatcher("listadoVid.jsp");
        a.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("title")!=null && request.getParameter("valuetitle")!=null){
            response.sendRedirect("listadoVid?filter=title&value="+request.getParameter("valuetitle"));
        }
        else if(request.getParameter("autor")!=null && request.getParameter("valueautor")!=null){
            response.sendRedirect("listadoVid?filter=autor&value="+request.getParameter("valueautor"));
        }
        else if(request.getParameter("year")!=null && request.getParameter("valueyear")!=null && !request.getParameter("valueyear").equals("") && StringUtils.isNumeric(request.getParameter("valueyear"))){
            response.sendRedirect("listadoVid?filter=year&value="+request.getParameter("valueyear"));
        }
        else {
            response.sendRedirect("listadoVid");
        }
    }
}

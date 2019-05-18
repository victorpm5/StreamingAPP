package com.isdcm.streamingapp.servlets;

import com.isdcm.streamingapp.services.DocumentService;
import com.isdcm.streamingapp.services.SecureXMLService;
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

        GetVideosRequest requestVideos = new GetVideosRequest();
        GetVideosResponse responseVideos = port.getVideos(requestVideos);
        videos = responseVideos.getVideo();

        request.setAttribute("videos", videos);
        RequestDispatcher a = request.getRequestDispatcher("listadoVid.jsp");
        a.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        VideosPortService portService = new VideosPortService();
        VideosPort port = portService.getVideosPortSoap11();
        List<Video> videos;

        if(request.getParameter("title")!=null && request.getParameter("valuetitle")!=null){

            FilterVideosByTitleRequest requestVideos = new FilterVideosByTitleRequest();
            requestVideos.setTitle(request.getParameter("valuetitle"));
            FilterVideosByTitleResponse responseVideos = port.filterVideosByTitle(requestVideos);
            videos = responseVideos.getVideo();

            request.setAttribute("videos", videos);
            RequestDispatcher a = request.getRequestDispatcher("listadoVid.jsp");
            a.forward(request, response);
        }
        else if(request.getParameter("autor")!=null && request.getParameter("valueautor")!=null){

            FilterVideosByAutorRequest requestVideos = new FilterVideosByAutorRequest();
            requestVideos.setAutor(request.getParameter("valueautor"));
            FilterVideosByAutorResponse responseVideos = port.filterVideosByAutor(requestVideos);
            videos = responseVideos.getVideo();

            request.setAttribute("videos", videos);
            RequestDispatcher a = request.getRequestDispatcher("listadoVid.jsp");
            a.forward(request, response);
        }
        else if(request.getParameter("year")!=null && request.getParameter("valueyear")!=null && !request.getParameter("valueyear").equals("") && StringUtils.isNumeric(request.getParameter("valueyear"))){

            FilterVideosByYearRequest requestVideos = new FilterVideosByYearRequest();
            requestVideos.setYear(Integer.parseInt(request.getParameter("valueyear")));
            FilterVideosByYearResponse responseVideos = port.filterVideosByYear(requestVideos);
            videos = responseVideos.getVideo();

            request.setAttribute("videos", videos);
            RequestDispatcher a = request.getRequestDispatcher("listadoVid.jsp");
            a.forward(request, response);
        } else if (request.getParameter("xml") != null){
            SecureXMLService.testEncriptionDeciption();
            response.sendRedirect("listadoVid");
        }
        else {
            response.sendRedirect("listadoVid");
        }
    }
}

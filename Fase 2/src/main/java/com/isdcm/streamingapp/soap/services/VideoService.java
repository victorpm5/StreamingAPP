package com.isdcm.streamingapp.soap.services;

import io.spring.guides.gs_producing_web_service.Video;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class VideoService {

    private static final String GetAllVideos = "SELECT * FROM Video";
    private static final String GetVideosByTitle = "SELECT * FROM Video WHERE title=?";

    public static List<Video> GetVideosByTitle(String title){
        List<Video> videos = new ArrayList<Video>();
        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement statement = conn.prepareStatement(GetVideosByTitle);
            statement.setQueryTimeout(30);

            statement.setString(1,title);

            ResultSet result = statement.executeQuery();

            while(result.next()){

                Date creacion = result.getTimestamp("fechaCreacion");

                GregorianCalendar gCal = new GregorianCalendar();
                gCal.setTime(creacion);
                XMLGregorianCalendar gDateUnformated = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCal);

                Video video = new Video();
                video.setTitle(result.getString("title"));
                video.setAutor(result.getString("autor"));
                video.setFechaCreacion(gDateUnformated);
                video.setDuracion(result.getFloat("duracion"));
                video.setNumReproducciones(result.getInt("numReproducciones"));
                video.setDescripcion(result.getString("descripcion"));
                video.setFormato(result.getString("formato"));
                video.setUrl(result.getString("url"));

                videos.add(video);
            }

        } catch (ClassNotFoundException | SQLException | DatatypeConfigurationException e) {
            System.out.println("holii, ha habido un error " + e);
        } finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                System.out.println("holii, ha habido un error " + e);
            }
        }

        return videos;
    }

}
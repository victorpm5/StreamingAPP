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
    private static final String GetVideosByTitle = "SELECT * FROM Video WHERE title LIKE ?";
    private static final String GetVideosByAutor = "SELECT * FROM Video WHERE autor lIKE ?";
    private static final String GetVideosByDate = "SELECT * FROM Video WHERE strftime('%Y',datetime(fechaCreacion/1000, 'unixepoch', 'localtime'))=?";

    public static List<Video> GetVideos(){
        List<Video> videos = new ArrayList<Video>();
        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement statement = conn.prepareStatement(GetAllVideos);
            statement.setQueryTimeout(30);

            executeGetVideosQuery(videos, statement);

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

    public static List<Video> GetVideosByTitle(String title){

        System.out.println("Cerca videos per Títol:" + title);

        return getVideos(title, GetVideosByTitle);
    }

    public static List<Video> GetVideosByAutor(String autor){

        System.out.println("Cerca videos per Autor:" + autor);

        return getVideos(autor, GetVideosByAutor);
    }

    public static List<Video> GetVideosByDate(int year) {

        System.out.println("Cerca videos per Any:" + year);

        List<Video> videos = new ArrayList<Video>();
        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement statement = conn.prepareStatement(GetVideosByDate);
            statement.setQueryTimeout(30);

            statement.setString(1,String.valueOf(year));

            executeGetVideosQuery(videos, statement);

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

    private static List<Video> getVideos(String title, String getVideosByTitle) {
        List<Video> videos = new ArrayList<Video>();
        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement statement = conn.prepareStatement(getVideosByTitle);
            statement.setQueryTimeout(30);

            statement.setString(1,"%"+title+"%");

            executeGetVideosQuery(videos, statement);

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

    private static void executeGetVideosQuery(List<Video> videos, PreparedStatement statement) throws SQLException, DatatypeConfigurationException {
        ResultSet result = statement.executeQuery();

        while(result.next()){

            Date creacion = result.getTimestamp("fechaCreacion");

            GregorianCalendar gCal = new GregorianCalendar();
            gCal.setTime(creacion);
            XMLGregorianCalendar gDateUnformated = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCal);

            Video video = new Video();
            video.setId(result.getInt("id"));
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
    }

}
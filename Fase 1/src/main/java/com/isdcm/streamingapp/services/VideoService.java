package com.isdcm.streamingapp.services;

import com.isdcm.streamingapp.models.Video;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideoService {

    private static final String InsertVideo = "INSERT INTO Video(title,autor,fechaCreacion,duracion,numReproducciones,descripcion,formato,url) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GetAllVideos = "SELECT * FROM Video";

    public static void NewVideo(Video video) throws SQLException, ClassNotFoundException {
        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement statement = conn.prepareStatement(InsertVideo);
            statement.setQueryTimeout(30);

            statement.setString(1, video.getTitle());
            statement.setString(2, video.getAutor());
            statement.setString(3, String.valueOf(video.getFechaCreacion().getTime()));
            statement.setString(4, String.valueOf(video.getDuracion()));
            statement.setString(5, String.valueOf(video.getNumReproducciones()));
            statement.setString(6, String.valueOf(video.getDescripcion()));
            statement.setString(7, String.valueOf(video.getFormato()));
            statement.setString(8, String.valueOf(video.getUrl()));

            statement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            throw e;

        } finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    public static List<Video> GetVideos() throws SQLException, ClassNotFoundException {

        List<Video> videos = new ArrayList<Video>();

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement statement = conn.prepareStatement(GetAllVideos);
            statement.setQueryTimeout(30);

            ResultSet result = statement.executeQuery();

            while(result.next()){

                Video video = new Video(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("autor"),
                        result.getTimestamp("fechaCreacion"),
                        result.getFloat("duracion"),
                        result.getInt("numReproducciones"),
                        result.getString("descripcion"),
                        result.getString("formato"),
                        result.getString("url")
                );

                videos.add(video);
            }

            return videos;

        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                throw e;
            }
        }
    }
}

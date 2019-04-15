package com.isdcm.streamingapp.rest.com.isdcm.streamingapp.rest.services;

import com.isdcm.streamingapp.rest.com.isdcm.streamingapp.rest.model.Video;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    private static final String GetAllVideos = "SELECT * FROM Video";

    @Override
    public  List<Video> GetVideos(){

        List<Video> videos = new ArrayList<Video>();

        try {
            Connection conn = DBConnection.getConnection();

            PreparedStatement statement = conn.prepareStatement(GetAllVideos);
            statement.setQueryTimeout(30);

            ResultSet result = statement.executeQuery();

            while(result.next()){

                Video video = new Video(
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

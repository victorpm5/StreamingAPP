package com.isdcm.streamingapp.soap;

import com.isdcm.streamingapp.models.Video;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

@Component
public class VideoRepository {
    private static final Map<String, Video> videos = new HashMap<>();

    @PostConstruct
    public void initData() throws DatatypeConfigurationException {

        GregorianCalendar gCal = new GregorianCalendar();
        gCal.setTime(new Date());
        XMLGregorianCalendar gDateUnformated = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCal);

        Video video1 = new Video();
        video1.setTitle("Cat");
        video1.setAutor("Loreto");
        video1.setFechaCreacion(gDateUnformated);
        video1.setDuracion(12);
        video1.setNumReproducciones(10);
        video1.setDescripcion("HOLIS");
        video1.setFormato(".mp4");
        video1.setUrl("https://youtube.com");

        videos.put(video1.getTitle(), video1);

    }

    public Video findVideo(String name) {
        Assert.notNull(name, "The video's name must not be null");
        return videos.get(name);
    }
}
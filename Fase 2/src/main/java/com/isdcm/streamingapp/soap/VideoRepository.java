package com.isdcm.streamingapp.soap;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

import io.spring.guides.gs_producing_web_service.Video;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

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

        Video video2 = new Video();
        video2.setTitle("Cat");
        video2.setAutor("Loreto");
        video2.setFechaCreacion(gDateUnformated);
        video2.setDuracion(12);
        video2.setNumReproducciones(10);
        video2.setDescripcion("HOLIS");
        video2.setFormato(".mp4");
        video2.setUrl("https://youtube.com");

        videos.put(video2.getTitle(), video2);

        Video video3 = new Video();
        video3.setTitle("Cat");
        video3.setAutor("Loreto");
        video3.setFechaCreacion(gDateUnformated);
        video3.setDuracion(12);
        video3.setNumReproducciones(10);
        video3.setDescripcion("HOLIS");
        video3.setFormato(".mp4");
        video3.setUrl("https://youtube.com");

        videos.put(video3.getTitle(), video3);
    }

    public Video findVideo(String name) {
        Assert.notNull(name, "The video's name must not be null");
        return videos.get(name);
    }

}
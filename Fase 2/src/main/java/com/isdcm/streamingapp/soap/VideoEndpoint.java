package com.isdcm.streamingapp.soap;

import io.spring.guides.gs_producing_web_service.*;
import com.isdcm.streamingapp.soap.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class VideoEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getVideosRequest")
    @ResponsePayload
    public GetVideosResponse getVideo (@RequestPayload GetVideosRequest request) {

        GetVideosResponse response = new GetVideosResponse();

        List<Video> videos = VideoService.GetVideos();
        for(int i = 0; i< videos.size(); ++i){
            response.getVideo().add(videos.get(i));
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "filterVideosByTitleRequest")
    @ResponsePayload
    public FilterVideosByTitleResponse getVideo(@RequestPayload FilterVideosByTitleRequest request) {

        FilterVideosByTitleResponse response = new FilterVideosByTitleResponse();

        List<Video> videos = VideoService.GetVideosByTitle(request.getTitle());
        for(int i = 0; i< videos.size(); ++i){
            response.getVideo().add(videos.get(i));
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "filterVideosByAutorRequest")
    @ResponsePayload
    public FilterVideosByAutorResponse getVideo(@RequestPayload FilterVideosByAutorRequest request) {

        FilterVideosByAutorResponse response = new FilterVideosByAutorResponse();

        List<Video> videos = VideoService.GetVideosByAutor(request.getAutor());
        for(int i = 0; i< videos.size(); ++i){
            response.getVideo().add(videos.get(i));
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "filterVideosByYearRequest")
    @ResponsePayload
    public FilterVideosByYearResponse getVideo(@RequestPayload FilterVideosByYearRequest request) {

        FilterVideosByYearResponse response = new FilterVideosByYearResponse();

        List<Video> videos = VideoService.GetVideosByDate(request.getYear());
        for(int i = 0; i< videos.size(); ++i){
            response.getVideo().add(videos.get(i));
        }

        return response;
    }
}
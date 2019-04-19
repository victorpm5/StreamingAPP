package com.example.demo;

import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class VideoEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private VideoRepository videoRepository;

    @Autowired
    public VideoEndpoint(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getVideoRequest")
    @ResponsePayload
    public GetVideoResponse getVideo(@RequestPayload GetVideoRequest request) {
        GetVideoResponse response = new GetVideoResponse();
        response.setVideo(videoRepository.findVideo(request.getName()));

        return response;
    }
}
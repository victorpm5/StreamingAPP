package com.isdcm.streamingapp.soap;

import com.isdcm.streamingapp.models.GetVideoRequest;
import com.isdcm.streamingapp.models.GetVideoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class VideoEndpoint {
    private static final String NAMESPACE_URI = "getVideo";

    private VideoRepository videoRepository;

    @Autowired
    public VideoEndpoint(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getVideoRequest")
    @ResponsePayload
    public GetVideoResponse getVideo(@RequestPayload GetVideoRequest request) {
        GetVideoResponse response = new GetVideoResponse();
        response.setCountry(videoRepository.findVideo(request.getTitle()));

        return response;
    }
}
package com.isdcm.streamingapp.rest.com.isdcm.streamingapp.rest.controller;

import com.isdcm.streamingapp.rest.com.isdcm.streamingapp.rest.model.Video;
import com.isdcm.streamingapp.rest.com.isdcm.streamingapp.rest.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/video")
public class VideoController {

	private final VideoService videoService;

	@Autowired
	public VideoController(VideoService videoService) {
		this.videoService = videoService;
	}


	@GetMapping
	public ResponseEntity<?> obteVideos() {

		List<Video> videos = videoService.GetVideos();

		return new ResponseEntity<>(videos,HttpStatus.OK);
	}

    @PutMapping(value="/reproduccio/{id}")
    public ResponseEntity<?> actualitzaReproduccions(
            @PathVariable final Integer id) {

		System.out.println("Reproducció video amb id: " + id);

	    Boolean resultat = videoService.augmentaReproduccions(id);

	    if (resultat) return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

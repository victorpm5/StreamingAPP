package com.isdcm.streamingapp.rest.com.isdcm.streamingapp.rest.controller;

import com.isdcm.streamingapp.rest.com.isdcm.streamingapp.rest.model.Video;
import com.isdcm.streamingapp.rest.com.isdcm.streamingapp.rest.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

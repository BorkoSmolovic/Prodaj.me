package com.bbtutorials.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbtutorials.users.entity.Images;
import com.bbtutorials.users.links.ImagesLinks;
import com.bbtutorials.users.service.ImagesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/")
public class ImagesController {
	
	@Autowired
	ImagesService imagesService;
	
	@GetMapping(path = ImagesLinks.LIST_IMAGES)
    public ResponseEntity<?> listImages() {
        List<Images> resource = imagesService.getImages();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = ImagesLinks.ADD_IMAGE)
	public ResponseEntity<?> saveImage(@RequestBody Images images) {
        Images resource = imagesService.saveImage(images);
        return ResponseEntity.ok(resource);
    }

}
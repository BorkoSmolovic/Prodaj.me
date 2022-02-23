package com.bbtutorials.users.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bbtutorials.users.entity.Images;
import com.bbtutorials.users.repository.ImagesRepository;



@Component
public class ImagesService {
	
	private ImagesRepository imagesRepository;

    public ImagesService (ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    public List<Images> getImages() {
        return imagesRepository.findAll();
    }
    
    public Images saveImage(Images images) {
    	return imagesRepository.save(images);
    }

}
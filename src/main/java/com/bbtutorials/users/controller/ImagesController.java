package com.bbtutorials.users.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.persistence.GeneratedValue;

import com.bbtutorials.users.entity.Images;
import com.bbtutorials.users.links.ImagesLinks;
import com.bbtutorials.users.service.ImagesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;

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

    @PutMapping("/upload/save/{id}")
    public String saveImages(@RequestParam("files") List<MultipartFile> multipartFiles, @PathVariable("id") int id) throws IOException {
        
        for (MultipartFile multipartFile : multipartFiles) {
        String random = UUID.randomUUID().toString();  
        Images img = new Images();
        img.setAdId(Long.parseLong(id+""));
        img.setImage("/photos/"+random+"."+multipartFile.getContentType().split("/")[1]);
        imagesService.saveImage(img);
 
        try {
            Path path = Paths.get("src/main/ui/public/photos/"+random+"."+multipartFile.getContentType().split("/")[1]);
            byte[] bytes = multipartFile.getBytes();
            Files.write(path, bytes);
        } catch (IOException e) {
           return "Greska: "+e.getMessage() + " " + e.getLocalizedMessage() + " " + e.toString();
        }
}
        return "Uspješno";
    }
}
package com.bbtutorials.users.controller;

import java.util.List;

import javax.annotation.Generated;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbtutorials.users.entity.Ads;
import com.bbtutorials.users.entity.Images;
import com.bbtutorials.users.links.AdsLinks;
import com.bbtutorials.users.repository.AdsRepository;
import com.bbtutorials.users.service.AdsService;
import com.bbtutorials.users.service.ImagesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class AdsController {

    @Autowired
    AdsService adsService;

    @GetMapping(path = AdsLinks.LIST_ADS)
    public ResponseEntity<?> listAds() {
        List<Ads> resource = adsService.getAds();
        return ResponseEntity.ok(resource);
    }

    @PostMapping(path = AdsLinks.ADD_AD)
    public ResponseEntity<?> saveAd(@RequestBody Ads ads) {
        Ads resource = adsService.saveAd(ads);
        return ResponseEntity.ok(resource);
    }

    // @PostMapping(path = AdsLinks.EDIT_AD)
    // public ResponseEntity<?> editAd(@PathVariable("id") int id, Model
    // model,@RequestBody Ads ads) {
    // Ads resource = adsService.findAdById(id);

    // model.addAttribute("ads", resource);
    // return ResponseEntity.ok(model);
    // }

    @PutMapping(path = AdsLinks.EDIT_AD)
    public ResponseEntity<?> updateUser(@RequestBody Ads ads, @PathVariable("id") int id) {
        Ads resource = adsService.saveAd(ads);
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping(path = AdsLinks.DELETE_AD)
    public ResponseEntity<?> deleteAd(@PathVariable(name = "id") Integer id) {
        Boolean resource = adsService.deleteAd(id);
        return ResponseEntity.ok(resource);
    }

}
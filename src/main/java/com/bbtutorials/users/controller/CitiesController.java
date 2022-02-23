package com.bbtutorials.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbtutorials.users.entity.Cities;
import com.bbtutorials.users.links.CitiesLinks;
import com.bbtutorials.users.service.CitiesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/")
public class CitiesController {
	
	@Autowired
	CitiesService citiesService;
	
	@GetMapping(path = CitiesLinks.LIST_CITIES)
    public ResponseEntity<?> listCities() {
        List<Cities> resource = citiesService.getCities();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = CitiesLinks.ADD_CITY)
	public ResponseEntity<?> saveCity(@RequestBody Cities cities) {
        Cities resource = citiesService.saveCity(cities);
        return ResponseEntity.ok(resource);
    }

}

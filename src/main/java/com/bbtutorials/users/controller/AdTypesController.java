package com.bbtutorials.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbtutorials.users.entity.AdTypes;
import com.bbtutorials.users.links.AdTypesLinks;
import com.bbtutorials.users.service.AdTypesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/")
public class AdTypesController {
	
	@Autowired
	AdTypesService adTypesService;
	
	@GetMapping(path = AdTypesLinks.LIST_TYPES)
    public ResponseEntity<?> listTypes() {
        List<AdTypes> resource = adTypesService.getAdTypes();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = AdTypesLinks.ADD_TYPE)
	public ResponseEntity<?> saveType(@RequestBody AdTypes adTypes) {
        AdTypes resource = adTypesService.saveAdType(adTypes);
        return ResponseEntity.ok(resource);
    }

}

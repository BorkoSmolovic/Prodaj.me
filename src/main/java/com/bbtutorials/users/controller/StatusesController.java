package com.bbtutorials.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbtutorials.users.entity.Statuses;
import com.bbtutorials.users.links.StatusesLinks;
import com.bbtutorials.users.service.StatusesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/")
public class StatusesController {
	
	@Autowired
	StatusesService statusesService;
	
	@GetMapping(path = StatusesLinks.LIST_STATUSES)
    public ResponseEntity<?> listStatuses() {
        List<Statuses> resource = statusesService.getStatuses();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = StatusesLinks.ADD_STATUS)
	public ResponseEntity<?> saveStatus(@RequestBody Statuses statuses) {
        Statuses resource = statusesService.saveStatus(statuses);
        return ResponseEntity.ok(resource);
    }

}

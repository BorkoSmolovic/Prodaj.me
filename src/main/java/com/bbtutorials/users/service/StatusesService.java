package com.bbtutorials.users.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bbtutorials.users.entity.Statuses;
import com.bbtutorials.users.repository.StatusesRepository;



@Component
public class StatusesService {
	
	private StatusesRepository statusesRepository;

    public StatusesService(StatusesRepository statusesRepository) {
        this.statusesRepository = statusesRepository;
    }

    public List<Statuses> getStatuses() {
        return statusesRepository.findAll();
    }
    
    public Statuses saveStatus(Statuses statuses) {
    	return statusesRepository.save(statuses);
    }

}
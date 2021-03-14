package com.bbtutorials.users.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bbtutorials.users.entity.AdTypes;
import com.bbtutorials.users.repository.AdTypesRepository;

@Component
public class AdTypesService {
	
	private AdTypesRepository adTypesRepository;

    public AdTypesService(AdTypesRepository adTypesRepository) {
        this.adTypesRepository = adTypesRepository;
    }

    public List<AdTypes> getAdTypes() {
        return adTypesRepository.findAll();
    }
    
    public AdTypes saveAdType(AdTypes adTypes) {
    	return adTypesRepository.save(adTypes);
    }

}
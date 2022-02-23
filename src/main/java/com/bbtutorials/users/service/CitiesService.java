package com.bbtutorials.users.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bbtutorials.users.entity.Cities;
import com.bbtutorials.users.repository.CitiesRepository;



@Component
public class CitiesService {
	
	private CitiesRepository citiesRepository;

    public CitiesService (CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    public List<Cities> getCities() {
        return citiesRepository.findAll();
    }
    
    public Cities saveCity(Cities cities) {
    	return citiesRepository.save(cities);
    }

}
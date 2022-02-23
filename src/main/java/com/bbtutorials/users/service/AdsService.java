package com.bbtutorials.users.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bbtutorials.users.entity.Ads;
import com.bbtutorials.users.repository.AdsRepository;



@Component
public class AdsService {
	
	private AdsRepository adsRepository;

    public AdsService (AdsRepository adsRepository) {
        this.adsRepository = adsRepository;
    }

    public List<Ads> getAds() {
        return adsRepository.findAll();
    }

    public Ads saveAd(Ads ads) {
    	return adsRepository.save(ads);
    }

    public Ads findAdById(int id){
        return adsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }
    
    public boolean deleteAd(int id) {
    	adsRepository.deleteById(id);
    	return true;
    }
 
    
    

}
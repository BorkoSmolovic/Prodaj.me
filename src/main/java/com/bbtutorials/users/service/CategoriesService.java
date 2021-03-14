package com.bbtutorials.users.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bbtutorials.users.entity.Categories;
import com.bbtutorials.users.repository.CategoriesRepository;


@Component
public class CategoriesService {
	
	private CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Categories> getCategories() {
        return categoriesRepository.findAll();
    }
    
    public Categories saveCategory(Categories categories) {
    	return categoriesRepository.save(categories);
    }

}
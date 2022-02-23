package com.bbtutorials.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbtutorials.users.entity.Categories;
import com.bbtutorials.users.links.CategoriesLinks;
import com.bbtutorials.users.service.CategoriesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/")
public class CategoriesController {
	
	@Autowired
	CategoriesService categoriesService;
	
	@GetMapping(path = CategoriesLinks.LIST_CATEGORIES)
    public ResponseEntity<?> listCategories() {
        List<Categories> resource = categoriesService.getCategories();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = CategoriesLinks.ADD_CATEGORY)
	public ResponseEntity<?> saveCategory(@RequestBody Categories categories) {
        Categories resource = categoriesService.saveCategory(categories);
        return ResponseEntity.ok(resource);
    }

}

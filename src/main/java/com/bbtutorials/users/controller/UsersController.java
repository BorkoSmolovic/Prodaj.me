package com.bbtutorials.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbtutorials.users.entity.Users;
import com.bbtutorials.users.links.UserLinks;
import com.bbtutorials.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/")
public class UsersController {
	
	@Autowired
	UsersService usersService;
	
	@GetMapping(path = UserLinks.LIST_USERS)
    public ResponseEntity<?> listUsers() {
        List<Users> resource = usersService.getUsers();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = UserLinks.ADD_USER)
	public ResponseEntity<?> saveUser(@RequestBody Users user) {
        Users resource = usersService.saveUser(user);
        return ResponseEntity.ok(resource);
    }

    @PostMapping(path = UserLinks.LOGIN_USER)
	public ResponseEntity<?> loginUser(@RequestBody Users user) {
        List<Users> resource = usersService.getUsers();
        for (Users users : resource) {
            if(users.getEmail().equalsIgnoreCase(user.getEmail()) && users.getPassword().equals(user.getPassword()) && users.getActive() == 1){
                return ResponseEntity.ok(users);
            }
        }
        return ResponseEntity.ok(null);
    }

    @PutMapping(path = UserLinks.EDIT_USER)
    public ResponseEntity<?> updateUser(@RequestBody Users user, @PathVariable("id") int id) {
        Users resource = usersService.saveUser(user);
        return ResponseEntity.ok(resource);
    }

}

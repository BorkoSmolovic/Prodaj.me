package com.bbtutorials.users.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bbtutorials.users.entity.Users;
import com.bbtutorials.users.repository.UsersRepository;

@Component
public class UsersService {
	
	private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }
    
    public Users saveUser(Users users) {
    	return usersRepository.save(users);
    }

    public boolean deleteUser(int id) {
    	usersRepository.deleteById(id);
    	return true;
    }

    public Users findAdById(int id){
        return usersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }
}

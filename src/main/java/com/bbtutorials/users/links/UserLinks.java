package com.bbtutorials.users.links;

import org.springframework.stereotype.Component;

@Component
public class UserLinks {
	
	public static final String LIST_USERS = "/users";
    public static final String ADD_USER = "/user/register";
    public static final String LOGIN_USER = "/user/login";
    public static final String EDIT_USER = "/user/edit/{id}";
    public static final String DELETE_USER = "/user/delete/{id}";


}

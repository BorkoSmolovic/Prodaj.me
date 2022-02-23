package com.bbtutorials.users.links;



	import org.springframework.stereotype.Component;

	@Component
	public class AdsLinks {
		public static final String LIST_ADS = "/ads";
	    public static final String ADD_AD = "/ad";
	    public static final String EDIT_AD = "/ad/edit/{id}";
	    public static final String DELETE_AD = "/ad/delete/{id}";

	}

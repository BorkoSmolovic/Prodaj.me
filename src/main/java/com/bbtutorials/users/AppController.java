package com.bbtutorials.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@CrossOrigin
@RequiredArgsConstructor
public class AppController {
	
	@RequestMapping({"/"})
	public String loadUI() {
		return "forward:/index.html";
	}

}

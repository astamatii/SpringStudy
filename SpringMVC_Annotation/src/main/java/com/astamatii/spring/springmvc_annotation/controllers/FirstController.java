package com.astamatii.spring.springmvc_annotation.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {

	//Transfer
	@GetMapping("/")
	public String homepage(HttpServletRequest request) {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		//name of the page by the template name
		
		System.out.println("Hello, " + name + " " + surname); 
		return "first/homepage";
	}
	
	@GetMapping("/1")
	public String firstpage(@RequestParam(value = "name", required = false) String name) {
		
		System.out.println("Hello, " + name);
		return "first/firstpage";
	}
	
	@GetMapping("/2")
	public String secondpage() {
		return "first/secondpage";
	}	
}

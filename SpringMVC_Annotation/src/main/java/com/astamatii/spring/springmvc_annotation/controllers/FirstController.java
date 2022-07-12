package com.astamatii.spring.springmvc_annotation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

	//Transfer
	@GetMapping("/")
	public String homepage() {
		//name of the page by the template name
		return "first/homepage";
	}
	
	@GetMapping("/1")
	public String firstpage() {
		return "first/firstpage";
	}
	
	@GetMapping("/2")
	public String secondpage() {
		return "first/secondpage";
	}	
}

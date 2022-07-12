package com.astamatii.spring.springmvc_annotation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("second")
public class SecondController {
	
	@GetMapping("/about")
	public String about() {
		return "second/about";
	}
	
}

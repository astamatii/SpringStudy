package com.astamatii.spring.springmvc_annotation.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {

	//Transfer
	@GetMapping("/")
	public String homepage(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		
		if(name == null) name = "Muchacho";
		if(surname == null) surname = "Amigo";
		
		//name of the page by the template name
		model.addAttribute("message", "Hello " + name + " " + surname + "!");
		
		return "first/homepage";
	}
	
	@GetMapping("/1")
	public String firstpage(@RequestParam(value = "name", required = false) String name,
			Model model) {
		
		if(name == null) name = "Muchacho";
		model.addAttribute("message", "Hello " + name + "!");
		
		return "first/firstpage";
	}	
}

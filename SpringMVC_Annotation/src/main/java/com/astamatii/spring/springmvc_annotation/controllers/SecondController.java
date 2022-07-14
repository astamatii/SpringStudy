package com.astamatii.spring.springmvc_annotation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("second")
public class SecondController {
	
	@GetMapping("/about")
	public String about() {
		return "second/about";
	}
	
	@GetMapping("/calc")
	public String calculator(@RequestParam("a") int a, 
			@RequestParam("b") int b, 
			@RequestParam("action") String action,
			Model model) {
		
		double result;
		
		switch(action) {
			case "*":
				result = a * b;
				break;
			case "/":
				result = a / (double)b;
				break;
			case "+":
				result = a + b;
				break;
			case "-":
				result = a - b;
				break;
			default:
				result = 0;
				break;
		}
		
		model.addAttribute("result", result);
		model.addAttribute("a", a);
		model.addAttribute("b", b);
		model.addAttribute("action", action);
		
		return "second/calculator";
	}
}

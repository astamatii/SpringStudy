package com.astamatii.spring.springmvc_annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	//Transfer
	@GetMapping("/")
	public String homepage() {
		//name of the page by the template name
		return "homepage";
	}
	
	@GetMapping("/1")
	public String firstpage() {
		//name of the page by the template name
		return "firstpage";
	}
	
	@GetMapping("/2")
	public String secondpage() {
		return "secondpage";
	}	
}
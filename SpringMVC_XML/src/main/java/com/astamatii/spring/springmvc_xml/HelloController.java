package com.astamatii.spring.springmvc_xml;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	//Transfer
	@GetMapping("/")
	public String firstpage() {
		//name of the page by the template name
		return "firstpage";
	}
	
	@GetMapping("/2")
	public String secondpage() {
		return "secondpage";
	}	
}

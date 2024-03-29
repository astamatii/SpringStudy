package com.astamatii.londonhotel.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.astamatii.londonhotel.domain.QuoteRequest;

@Controller
public class QuoteRequestManagementController {
    
//	@GetMapping(path = "/quoteRequests")
//    public String listRequests() {
//        
//        
//    		return "quoteRequestList";
//    }
	
	@GetMapping(path = "/quoteRequests", params = "!eventType")
    public String listRequests() {
        
        
    	return "quoteRequestList";
    }
	
	@GetMapping("/quoteRequest/{quotedId}")
	public ModelAndView viewQuoteRequest(@PathVariable int quoteId) {
    	QuoteRequest quoteRequestBean = new QuoteRequest();
    	quoteRequestBean.setBudget(5000);
    	quoteRequestBean.setEventType("wedding");
    	
    	ModelAndView mav =  new ModelAndView();
    	mav.addObject("quoteRequestBean", quoteRequestBean);
    	mav.setViewName("quoteRequestDetail");
    	
    	return mav;
    }
	
	@GetMapping("/quoteRequest/social/{quotedId}")
	public String viewQuoteRequestSocial(@PathVariable int quoteId) {
    	String returnViewName = "quoteRequestSocialEventDetail";
    	
    	boolean someCondition = true;
    	if (someCondition) {
    		returnViewName = "redirect:/quoteRequest/social/{quoteId}";
    	}
    	
    	return returnViewName;
    }
	
	@GetMapping
	@ResponseBody
	public QuoteRequest viewQuoteRequestApi() {
    	
		// add some implementation here
		
		QuoteRequest quoteRequest = new QuoteRequest();
		quoteRequest.setBudget(5000);
    	
    	return quoteRequest;
    }
	
	@PostMapping("/quoteDetail")
    public String updateQuoteRequest(@ModelAttribute QuoteRequest formBean) {
        
		// implement a save of all of the form bean information
        
    	return "quoteRequestDetail";
    }
	
	
	@ModelAttribute
	public void addCommonAttributes(@RequestParam String eventType,
			Model model) {
		String customMessage = "You are viewing request for " 
			+ eventType;
		model.addAttribute("eventTypeMessage", customMessage);
	}
	
	//	This is already implemented in QuoteRequestManagementAdvice class
//	@InitBinder
//	public void addCommonInitBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		dateFormat.setLenient(false);
//		
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//	}
}

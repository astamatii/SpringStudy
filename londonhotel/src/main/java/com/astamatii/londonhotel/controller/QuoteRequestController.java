package com.astamatii.londonhotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.astamatii.londonhotel.domain.QuoteRequest;

@Controller
public class QuoteRequestController {

	@GetMapping("/newquote")
    public String beginQuoteRequest(Model model) {
		// add implementation later
        
		model.addAttribute("quoteRequestForm", new QuoteRequest());
		
    	return "newQuote";
    }
    
	@PostMapping("/newquote")
    public String submitQuoteRequest(@ModelAttribute QuoteRequest formBean) {
        // add implementation later
        
    	return "newQuoteConfirmation";
    }

}

package com.astamatii.hanselnpetal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.astamatii.hanselnpetal.domain.ContactImportantOccasion;
import com.astamatii.hanselnpetal.domain.CustomerContact;
import com.astamatii.hanselnpetal.service.ContactsManagementService;

@Controller
public class ContactsManagementController {

	@Autowired
	private ContactsManagementService contactsManagementService;
	
	
	@RequestMapping(value= "/addContact", method=RequestMethod.POST )
	public String processAddContactSubmit( @RequestBody  CustomerContact aContact) {
		
		CustomerContact newContact = contactsManagementService.add(aContact);
		
		if (newContact != null) {
			return "success";
		}
		
		return "failure";
	}
	
	@RequestMapping(value= "/addContactThymeleaf", method=RequestMethod.POST)
	public String processAddContactSubmitThymeleaf(@ModelAttribute CustomerContact aContact) {
		
		CustomerContact newContact = contactsManagementService.add(aContact);
		
		if (newContact != null) {
			return "/addContactForm";
		}
		
		return "redirect:/showAddContact";
	}
	
	@RequestMapping(value= "/showAddContact", method=RequestMethod.GET)
	public String showAddContact() {
		
		// implement this
		
		return "/addContactForm";
	}
	
	public String processAddContactOccasionSubmit(@ModelAttribute CustomerContact aContact, 
			@ModelAttribute ContactImportantOccasion anOccasion) {
		
		// implement this
		
		return "/addContactOccasionForm";
	}
}

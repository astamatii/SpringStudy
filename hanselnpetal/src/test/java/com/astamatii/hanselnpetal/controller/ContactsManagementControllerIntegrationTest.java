package com.astamatii.hanselnpetal.controller;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.astamatii.hanselnpetal.domain.CustomerContact;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class ContactsManagementControllerIntegrationTest {

	@Autowired
	private ContactsManagementController contactsManagementController;
	
	@Test
	void testAddContactHappyPath() {
		// Create a contact
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName("Jenny");
		aContact.setLastName("Johnson");
		
		// POST our CustomerContact form bean to the controller; check outcome
		String outcome = contactsManagementController.processAddContactSubmit(aContact);
		
		//Assert THAT the outcome is as expected
		assertThat(outcome, is(equalTo("success")));
		
	}
	
	@Test
	public void testAddContactFirstNameMissing() {
		// Create an empty contact
		CustomerContact aContact = new CustomerContact();
		
		// POST our CustomerContact form bean to the controller; check outcome
		String outcome = contactsManagementController.processAddContactSubmit(aContact);
		
		//Assert THAT the outcome is as expected
		assertThat(outcome, is(equalTo("failure")));
		
	}
	

}

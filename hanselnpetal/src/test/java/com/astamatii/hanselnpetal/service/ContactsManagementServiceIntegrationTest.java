package com.astamatii.hanselnpetal.service;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.astamatii.hanselnpetal.domain.CustomerContact;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class ContactsManagementServiceIntegrationTest {

	@Autowired
	private ContactsManagementService contactsManagementService;
	
	@Test
	void testAddContactHappyPath() {
		//Create a contact
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName("Jenny");
		aContact.setLastName("Johnson");
		
		// Test adding the contact
		CustomerContact newContact = contactsManagementService.add(aContact);
		
		//Verify the addition
		assertNotNull(newContact);
		assertNotNull(newContact.getId());
		assertEquals("Jenny", newContact.getFirstName());
		
	}
	
	@Test
	void testAddContactBadPath() {
		//Create a contact
		CustomerContact aContact = new CustomerContact();
		
		// Test adding the contact
		CustomerContact newContact = contactsManagementService.add(aContact);
		
		//Verify the addition
		assertNull(newContact);
		
	}

}

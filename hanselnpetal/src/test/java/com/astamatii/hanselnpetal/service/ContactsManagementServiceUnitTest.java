package com.astamatii.hanselnpetal.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.astamatii.hanselnpetal.data.repos.CustomerContactRepository;
import com.astamatii.hanselnpetal.domain.CustomerContact;

@RunWith(MockitoJUnitRunner.class) // with this annotation it`s not necessary to openMocks()
//@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ExtendWith(MockitoExtension.class) // this annotation ports the functionalities from the MockitoJUnitRunner into the new extension model
class ContactsManagementServiceUnitTest {

	@Mock
	private CustomerContactRepository customerContactRepository;
	
	@InjectMocks
	private ContactsManagementService contactsManagementService;
	
//	@BeforeEach
//	public void setup() { // old (depricated) version of openMocks()
//		MockitoAnnotations.initMocks(this);
//	}
	
//	@BeforeEach
//	public void init() {
//	    MockitoAnnotations.openMocks(this);
//	}
	
	@Test
	void testAddContactUnitHappyPath() {
		// Create a contact
		CustomerContact aMockContact = new CustomerContact();
		aMockContact.setFirstName("Jenny");
		aMockContact.setLastName("Johnson");
		
		when(customerContactRepository.save(any(CustomerContact.class)))
			.thenReturn(aMockContact);
		
		// Save the contact
		CustomerContact newContact = contactsManagementService.add(aMockContact);
		
		// Verify the save
		assertEquals("Jenny", newContact.getFirstName());
	}

	@Test
	void testAddContactUnitBadPath() {
		// Create a contact
		CustomerContact aMockContact = new CustomerContact();
		
		// Save the contact
		CustomerContact newContact = contactsManagementService.add(aMockContact);
		
		// Verify the save
		assertNull(newContact);
	}
	
}

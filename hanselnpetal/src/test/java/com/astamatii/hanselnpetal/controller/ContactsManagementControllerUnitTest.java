package com.astamatii.hanselnpetal.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.astamatii.hanselnpetal.HelperMethods;
import com.astamatii.hanselnpetal.domain.CustomerContact;
import com.astamatii.hanselnpetal.service.ContactsManagementService;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactsManagementController.class)
class ContactsManagementControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ContactsManagementService contactsManagementService;
	
	@InjectMocks
	private ContactsManagementController contactsManagementController;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}	
	
	@Test
	void testAddJsonContactHappyPath() throws Exception{
		// setup mock Contact returned the mock service component
		CustomerContact mockCustomerContact = new CustomerContact();
		mockCustomerContact.setFirstName("Fred");
				
		when(contactsManagementService.add(any(CustomerContact.class)))
		.thenReturn(mockCustomerContact);
		
		// simulate the form bean that would POST from the web page
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName("Fred");
		aContact.setEmail("fredj@myemail.com");
				
		// simulate the form submit (POST)
		mockMvc
			.perform(
					MockMvcRequestBuilders
						.post("/addContact")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(HelperMethods.asJsonString(aContact)) /* "{\"firstName\":\"Fred\",\"email\":\"fredj@myemail.com\"}" */
					)
			.andExpect(
					MockMvcResultMatchers
						.status().isOk()
					)
			.andReturn();
	}
	
	@Test
	public void testAddJsonContactBizServiceRuleNotSatisfied() throws Exception {
		// setup a mock response of NULL object returned from the mock service component
		when(contactsManagementService.add(any(CustomerContact.class)))
			.thenReturn(null);
		
		// simulate the form bean that would POST from the web page
		CustomerContact aContact = new CustomerContact();
		aContact.setLastName("Johnson");
				
		// simulate the form submit (POST)
		mockMvc
			.perform (
					MockMvcRequestBuilders
						.post("/addContact")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(HelperMethods.asJsonString(aContact))
					)
			.andExpect(
					MockMvcResultMatchers
						.status().isOk()
					)
			.andReturn();

	}
	
	@Test
	void testAddThymeleafContactHappyPath() throws Exception{
		// setup mock Contact returned the mock service component
		CustomerContact mockCustomerContact = new CustomerContact();
		mockCustomerContact.setFirstName("Fred");
				
		when(contactsManagementService.add(any(CustomerContact.class)))
		.thenReturn(mockCustomerContact);
		
		// simulate the form bean that would POST from the web page
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName("Fred");
		aContact.setEmail("fredj@myemail.com");
				
		// simulate the form submit (POST)
		mockMvc
			.perform(
					MockMvcRequestBuilders
						.post("/addContactThymeleaf")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(HelperMethods.asJsonString(aContact)) /* "{\"firstName\":\"Fred\",\"email\":\"fredj@myemail.com\"}" */
					)
			.andExpect(
					MockMvcResultMatchers
						.status().isOk()
					)
			.andReturn();
	}
	
	@Test
	public void testAddThymeleafContactBizServiceRuleNotSatisfied() throws Exception {		
		// setup a mock response of NULL object returned from the mock service component
		when(contactsManagementService.add(any(CustomerContact.class)))
			.thenReturn(null);
		
		// simulate the form bean that would POST from the web page
		CustomerContact aContact = new CustomerContact();
		aContact.setLastName("Johnson");
				
		// simulate the form submit (POST)
		mockMvc
			.perform (
					MockMvcRequestBuilders
						.post("/addContactThymeleaf")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(HelperMethods.asJsonString(aContact))
					)
			.andExpect(
					MockMvcResultMatchers
						.status().is(302)
					)
			.andReturn();

	}

}

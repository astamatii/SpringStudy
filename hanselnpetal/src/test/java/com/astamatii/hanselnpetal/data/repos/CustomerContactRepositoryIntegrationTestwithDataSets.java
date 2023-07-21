package com.astamatii.hanselnpetal.data.repos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.astamatii.hanselnpetal.domain.CustomerContact;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class
})
@DatabaseSetup("classpath:test-datasets.xml")
class CustomerContactRepositoryIntegrationTestwithDataSets {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CustomerContactRepository customerContactRepository;
	
	@Test
	void testFindByEmail() {
		// setup data scenario
		CustomerContact aNewContact = new CustomerContact();
		aNewContact.setEmail("fredj@myemail.com");
		
		// find an inserted record using repository class
		CustomerContact foundContact = customerContactRepository.findByEmail("fredj@myemail.com");
		
		// Assertion
		assertThat(foundContact.getEmail(), is(equalTo("fredj@myemail.com")));
	}
}

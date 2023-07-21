package com.astamatii.hanselnpetal.data.repos;

import org.springframework.data.repository.CrudRepository;

import com.astamatii.hanselnpetal.domain.CustomerContact;

public interface CustomerContactRepository extends CrudRepository<CustomerContact, Long> {

	public CustomerContact findByEmail(String email);
}

package com.astamatii.spring.snippets.springboot3;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication //@Configuration + @EnableAutoConfiguration + @ComponentScan(basePackages = "com.astamatii.spring.snippets.springboot3")
@RestController
@RequestMapping("api/v1/customers")
public class Springboot3Application {
	
	private final CustomerRepository customerRepository;
	
	public Springboot3Application (CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		
	}

	public static void main(String[] args) {
		SpringApplication.run(Springboot3Application.class, args);
	}
	
	@GetMapping
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}
	
	record NewCustomerRequest (
			String name,
			String email,
			Integer age
			) {}
	
	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest request) {
		Customer customer = new Customer();
		customer.setName(request.name());
		customer.setEmail(request.email());
		customer.setAge(request.age());
		customerRepository.save(customer);
	}
	
	@DeleteMapping("{id}")
	public void deleteCustomer(@PathVariable("id") Long id) {
		customerRepository.deleteById(id);
	}
	
	@PutMapping("{id}")
	public void updateCustomer(@PathVariable("id") Long id, @RequestBody NewCustomerRequest request) {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setName(request.name());
		customer.setEmail(request.email());
		customer.setAge(request.age());
		customerRepository.save(customer);
		
	}
	
	@GetMapping("/greet")
	public GreetResponse greet() {
		return new GreetResponse(
				"Hello",
				List.of("Java", "Golang", "JavaScript"),
				new int[] {1, 2, 3 ,4},
				new Person("Alex", LocalDate.of(1999, 7, 25), 30.6)
				);
	}
	
	record Person (String name, LocalDate dob, double money) {}
	
	record GreetResponse (
			String greet,
			List<String> languages,
			int[] numbers,
			Person person
			) {}
	
//	class GreetResponse {  // (same thing as record GreetResponse(String greet)
//		private final String greet;
//		
//		GreetResponse(String greet) {
//			this.greet = greet;
//		}
//
//		public String getGreet() {
//			return greet;
//		}
//
//		@Override
//		public String toString() {
//			return "GreetResponse [greet=" + greet + "]";
//		}
//
//		@Override
//		public int hashCode() {
//			final int prime = 31;
//			int result = 1;
//			result = prime * result + getEnclosingInstance().hashCode();
//			result = prime * result + Objects.hash(greet);
//			return result;
//		}
//
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
//			if (obj == null)
//				return false;
//			if (getClass() != obj.getClass())
//				return false;
//			GreetResponse other = (GreetResponse) obj;
//			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
//				return false;
//			return Objects.equals(greet, other.greet);
//		}
//
//		private Springboot3Application getEnclosingInstance() {
//			return Springboot3Application.this;
//		}		
//	}

}

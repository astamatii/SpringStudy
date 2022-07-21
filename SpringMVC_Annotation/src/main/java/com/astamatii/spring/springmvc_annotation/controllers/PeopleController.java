package com.astamatii.spring.springmvc_annotation.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.astamatii.spring.springmvc_annotation.dao.PersonDao;
import com.astamatii.spring.springmvc_annotation.models.Person;



@SuppressWarnings("unused")
@Controller
@RequestMapping("/people") //for link
public class PeopleController {
	
	private PersonDao personDao;

	@Autowired
	public PeopleController(PersonDao personDao) {
		this.personDao = personDao;
	}

	@GetMapping() //Web-page for showing all Person objects
	public String index(Model model) {
		model.addAttribute("people", personDao.index());
		return "people/index"; //for path
	}	

	@GetMapping("/{id}") //Web-page for showing a Person object fields
	//@PathVariable - извлекает id из URL(наверху), и передает его в параметры(внизу)
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", personDao.show(id));
		return "people/show";
	}
	
	@GetMapping("/new") //Метод контроллера для работы с представлением
	public String newPerson(Model model) {
		//Required for the HTML form with Thymeleaf at new.html
		model.addAttribute("person", new Person()); 
		return "people/new";
	}
	
	//Method for POST-request with @ModelAttribute
	@PostMapping() //Метод контроллера для работы с моделью через DAO
	public String create(@ModelAttribute("person") @Valid Person person, 
						BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			//after return it again will use person object with errors,
			//that will be captured by thymeleaf
			return "people/new"; 
		
		personDao.save(person);
		return "redirect:/people"; //return to /people/index.html
	}
	
	//Old Method for POST-request with @RequestParam
//	@PostMapping() //Метод контроллера для работы с моделью через DAO
//	public String create_manual(@RequestParam("name") String name, Model model) {
//		Person person = new Person();
//		
//		person.setName(name);
//		
//		personDao.save(person);
//		
//		model.addAttribute("person", person);
//		return "redirect:/people"; //return to /people/index.html
//	}
	
	@GetMapping("/{id}/edit") //Web-page for editing a Person object
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", personDao.show(id));
		return "people/edit";
	}
	
	@PatchMapping("/{id}")
	public String update(@PathVariable("id") int id, 
						@ModelAttribute("person") @Valid Person person, 
						BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			//after return it again will use person object with errors,
			//that will be captured by thymeleaf
			return "people/edit"; 
		
		personDao.update(id, person);
		return "redirect:/people";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		personDao.delete(id);
		return "redirect:/people";
	}
}

package com.astamatii.spring.springmvc_annotation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.astamatii.spring.springmvc_annotation.dao.PersonDao;
import com.astamatii.spring.springmvc_annotation.models.Person;

@Controller
@RequestMapping("/people") //for link
public class PeopleController {
	
	private PersonDao personDao;

	@Autowired
	public PeopleController(PersonDao personDao) {
		this.personDao = personDao;
	}

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("people", personDao.index());
		return "people/index"; //for path
	}	

	@GetMapping("/{id}")
	//@PathVariable - извлекает id из URL(наверху), и передает его в параметры(внизу)
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", personDao.show(id));
		return "people/show";
	}
	
	@GetMapping("/new")
	public String newPerson(Model model) {
		model.addAttribute("person", new Person());
		return "people/new";
	}
	
	@PostMapping()
	public String create(@ModelAttribute("person") Person person) {
		personDao.save(person);
		return "redirect:/people"; //return to /people/index.html
	}
	
}

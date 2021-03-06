package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

@Controller
public class ApplicationController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/")
	public String index(Model model) { return "index"; }

	@GetMapping("/list")
	public String getPeople(Model model) {
		List<Person> people = null;
		
		try {
			people = (List<Person>) personRepository.findAll();
		}
		catch (Exception e) {}
		model.addAttribute("people", people);
		
		return "list";
	}
	
	@GetMapping("/add")
	public String addPerson(Model model) {
		model.addAttribute("person", new Person());
		
		return "add";
	}
	
	@PostMapping("/save")
	public String savePerson(@Valid @ModelAttribute Person person, BindingResult errors, Model model) {
        if (!errors.hasErrors()) {
            try {
            	personRepository.save(person);
            }
            catch (Exception e) { System.out.println(e); }
        	
        	return this.getPeople(model);
        }
        else {
        	return "add";
        }
    }
	
	@GetMapping("/delete/{id}")
	public String deletePerson(@PathVariable("id") Integer id, Model model) {
		try {
			personRepository.deleteById(id);
		}
		catch (Exception e) { System.out.println(e); }
		
		return this.getPeople(model);
	}
	
	@GetMapping("/update/{id}")
	public String updatePerson(@PathVariable("id") Integer id, Model model) {
		Person person = null;
		try {
			person = personRepository.findById(id).get();
			model.addAttribute("person", person);
			
			System.out.println("Update id: " + person.getId());
			return "update";
		}
		catch (Exception e) { System.out.println(e); }
		
		return "list";
	}
	
	@PostMapping("/saveUpdate")
	public String saveUpdatePerson(@Valid @ModelAttribute Person person, BindingResult errors, Model model) {
		if (!errors.hasErrors()) {
            try {
            	System.out.println("save Update id: " + person.getId());
            	personRepository.save(person);
            }
            catch (Exception e) { System.out.println(e); }
        	
        	return this.getPeople(model);
        }
        else {
        	return "update/" + person.getId();
        }
	}
}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

@RestController
public class ApplicationController {
	
	@Autowired
	private PersonRepository personRepository;

	@RequestMapping(value="/person", method=RequestMethod.GET)
	public String getAllUsers() {
		List<Person> people;
		
		try {
			people = (List<Person>) personRepository.findAll();
		}
		catch (Exception e) { return "exception"; }
		
		return people.toString();
	}
}

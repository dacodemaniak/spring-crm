package com.aelion.mycrm.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.mycrm.models.Person;
import com.aelion.mycrm.services.ContactService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private ContactService contactService;
	
	@GetMapping("/all")
	@CrossOrigin
	public List<String> getDummy() {
		// Your logic here !
		ArrayList<String> persons = new ArrayList<String>();
		persons.add("Aubert");
		persons.add("Casper");
		
		return persons;
	}
	
	@GetMapping()
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<List<Person>> getPersons() {
		ArrayList<Person> persons = (ArrayList<Person>) this.contactService.all();
		
		return ResponseEntity.status(HttpStatus.OK).body(persons);
	}
	
	@PostMapping()
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<Person> addPerson(@RequestBody() Person person) {
		return new ResponseEntity<Person>(
			this.contactService.save(person), 
			HttpStatus.CREATED
		);
	}
}

package com.nguyentran.elasticsearch.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyentran.elasticsearch.entity.Person;
import com.nguyentran.elasticsearch.respository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public List<Person> getAll() {	
		Iterable<Person> iPerson = personRepository.findAll();
		List<Person> listPersons = new ArrayList<>();
		iPerson.forEach(person ->{
			listPersons.add(person);
		});
		
		return listPersons;
	}

	public Person create(Person person) {
		return personRepository.save(person);
	}

	public Person findById(String id) {
		
		return personRepository.findById(id).get();
	}
}

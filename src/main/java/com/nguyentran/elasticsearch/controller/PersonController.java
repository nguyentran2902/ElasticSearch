package com.nguyentran.elasticsearch.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyentran.elasticsearch.entity.Person;
import com.nguyentran.elasticsearch.service.PersonService;


@RestController
@RequestMapping("/admin/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll() {
		HashMap<String, Object> map = new HashMap<>();
		try {
			List<Person> listPerson = personService.getAll();
			if(listPerson==null) {
				map.put("status", true);
				map.put("data", "no content");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
			}
			
			map.put("status", true);
			map.put("data", listPerson);
			return ResponseEntity.ok(map);
			
		} catch (Exception e) {
			map.put("status", true);
			map.put("data", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
		
	}
	
	//find by Id
//	@GetMapping("/findById/{id}")
//	public ResponseEntity<?> findById(@PathVariable String id) {
//		HashMap<String, Object> map = new HashMap<>();
//		
//		try {
//			Person person = personService.findById(id);
//			if(person==null) {
//				map.put("status", true);
//				map.put("data", "no content");
//				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
//			}
//			map.put("status", true);
//			map.put("data", person);
//			return ResponseEntity.ok(map);
//			
//		} catch (Exception e) {
//			map.put("status", false);
//			map.put("data", e.getMessage());
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
//		}
//		
//	}

	//create
	@PostMapping("/create")
	public ResponseEntity<?> createPerson(@RequestBody Person person) {
		HashMap<String, Object> map = new HashMap<>();
		if (person == null) {
			map.put("status", false);
			map.put("data", "invalid input???");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(map);
		}
		
		try {
			Person psave = personService.create(person);
			if (psave != null) {
				map.put("status", true);
				map.put("data", psave);
				return ResponseEntity.ok(map);
			}
			map.put("status", false);
			map.put("data", "no content");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		} catch (Exception e) {
			map.put("status", false);
			map.put("data", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
		
			

		
	}
}

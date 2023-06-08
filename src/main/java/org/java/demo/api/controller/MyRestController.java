package org.java.demo.api.controller;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Pizza;
import org.java.demo.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class MyRestController {

	@Autowired
	private PizzaServ pizzaServ; 
	
	@GetMapping("/pizze")
	public ResponseEntity<List<Pizza>> getPizzaIndex(
			@RequestBody(required = false) String name
		) {
		
		List<Pizza> pizze = (name == null || name.isBlank())
				? pizzaServ.findAll()
				: pizzaServ.findByNome(name);
		
		return new ResponseEntity<>(pizze, HttpStatus.OK);
	}
	
	@GetMapping("/pizze/{id}")
	public ResponseEntity<Pizza> getPizzaById(
			@PathVariable int id
		) {
		
		Optional<Pizza> optPizza = pizzaServ.findById(id);
		
		if (optPizza.isEmpty()) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(optPizza.get(), HttpStatus.OK);
	}
	
	@PostMapping("/pizze")
	public ResponseEntity<Pizza> storePizza(
			@RequestBody(required = true) Pizza pizza
		) {
		
		pizza = pizzaServ.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	@PutMapping("/pizze")
	public ResponseEntity<Pizza> updatePizza(
			@RequestBody(required = true) Pizza pizza
		) {
		
		pizza = pizzaServ.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	@DeleteMapping("/pizze/{id}")
	public ResponseEntity<Pizza> deletePizza(
			@PathVariable int id
		) {
		
		Optional<Pizza> optPizza = pizzaServ.findById(id);
		
		if (optPizza.isEmpty()) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Pizza pizza = optPizza.get();
		
		pizza.getIngredienti().clear();
		pizzaServ.save(pizza);
		pizzaServ.delete(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
}

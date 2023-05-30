package org.java.demo.controller;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Pizza;
import org.java.demo.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@Autowired
	private PizzaServ pizzaServ;
	
	@GetMapping("/")
	public String getPizzaIndex(Model model) {
		
		List<Pizza> pizze = pizzaServ.findAll();
		model.addAttribute("pizze", pizze);
		
		return "pizza-index";
	}
	
	@PostMapping("/pizze/search")
	public String getPizzeByName(
			Model model,
			@RequestParam("search") String name
			) {
		
		List<Pizza> pizze = pizzaServ.findByNome(name);
		model.addAttribute("pizze", pizze);
		model.addAttribute("search", name);
		
		return "pizza-index";
	}
	
	@GetMapping("/pizze/new")
	public String createNewPizza() {
		
		return "pizza-new";
	}
	
	@PostMapping("/pizze/new")
	public String storeNewPizza(
			@ModelAttribute Pizza pizza
			) {
		
		pizzaServ.save(pizza);
		
		return "redirect:/pizze/" + pizza.getId();
	}
	
	@GetMapping("/pizze/{id}")
	public String getPizzaShow(
			Model model,
			@PathVariable int id
		) {
		
		Optional<Pizza> pizzaOpt = pizzaServ.findById(id);
		Pizza pizza = pizzaOpt.get();
		
		model.addAttribute("pizza", pizza);
		
		return "pizza-show";
	}
}

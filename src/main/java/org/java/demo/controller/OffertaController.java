package org.java.demo.controller;

import java.util.List;

import org.java.demo.pojo.Offerta;
import org.java.demo.pojo.Pizza;
import org.java.demo.serv.OffertaServ;
import org.java.demo.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class OffertaController {

	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private OffertaServ offertaServ;
	
	@GetMapping("/pizze/{id}/offerte/create")
	public String createNewOfferta(
			Model model,
			@PathVariable int id
		) {
		
		Pizza pizza = pizzaServ.findById(id).get();
		Offerta offerta = new Offerta();
		offerta.setPizza(pizza);
		
		model.addAttribute("offerta", offerta);
		
		return "offerta-new";
	}
	@PostMapping("/offerte/create")
	public String storeNewOfferta(
			Model model,
			@Valid @ModelAttribute Offerta offerta,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors())
				System.err.println(err.getDefaultMessage());
			
			model.addAttribute("offerta", offerta);
			model.addAttribute("errors", bindingResult);
			
			return "offerta-new";
		}
		
		offertaServ.save(offerta);
		
		return "redirect:/pizze/" + offerta.getPizza().getId();
	}
	
	@GetMapping("/offerta/update/{id}")
	public String updateOfferta(
			Model model,
			@PathVariable int id
		) {
		
		List<Pizza> pizze = pizzaServ.findAll();
		
		Offerta offerta = offertaServ.findById(id).get();
		model.addAttribute("offerta", offerta);
		model.addAttribute("pizze", pizze);
		
		return "offerta-update";
	}
	@PostMapping("/offerta/update/{id}")
	public String storeOfferta(
			Model model,
			@Valid @ModelAttribute Offerta offerta,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors())
				System.err.println(err.getDefaultMessage());
//			bindingResult.getAllErrors().stream()
//				.map(ObjectError::getDefaultMessage)
//				.forEach(System.err::println);
			
			List<Pizza> pizze = pizzaServ.findAll();
			
			model.addAttribute("pizze", pizze);
			model.addAttribute("offerta", offerta);
			model.addAttribute("errors", bindingResult);
			
			return "offerta-update";
		}
		
		offertaServ.save(offerta);
		
		return "redirect:/pizze/" + offerta.getPizza().getId();
	}
}

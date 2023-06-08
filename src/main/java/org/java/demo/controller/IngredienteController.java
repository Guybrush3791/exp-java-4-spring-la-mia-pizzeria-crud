package org.java.demo.controller;

import java.util.List;

import org.java.demo.pojo.Ingrediente;
import org.java.demo.pojo.Pizza;
import org.java.demo.serv.IngredienteServ;
import org.java.demo.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {

	@Autowired
	private IngredienteServ ingredienteServ;
	
	@Autowired
	private PizzaServ pizzaServ;
	
	@GetMapping
	public String getIngredienteIndex(Model model) {
		
		List<Ingrediente> ingredienti = ingredienteServ.findAll();
		
		model.addAttribute("ingredienti", ingredienti);
		
		return "ingrediente-index";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteIngrediente(
			@PathVariable int id
		) {
		
		Ingrediente ing = ingredienteServ.findById(id).get();
		
		for (Pizza p : ing.getPizze()) {
			
			p.removeIngrediente(ing);
			pizzaServ.save(p);
		}
		
		ingredienteServ.delete(ing);
		
		return "redirect:/ingredienti";
	}
	
	@GetMapping("/create")
	public String createNewIngrediente(Model model) {
		
		model.addAttribute("ingrediente", new Ingrediente());
		
		return "ingrediente-create";
	}
	@PostMapping("/create")
	public String storeNewIngrediente(
			Model model,
			@ModelAttribute Ingrediente ingrediente
		) {
		
		ingredienteServ.save(ingrediente);
		
		return "redirect:/ingredienti";
	}
}

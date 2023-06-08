package org.java.demo.serv;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Ingrediente;
import org.java.demo.repo.IngredienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteServ {

	@Autowired
	private IngredienteRepo ingredienteRepo;
	
	public List<Ingrediente> findAll() {
		
		return ingredienteRepo.findAll();
	}
	public Optional<Ingrediente> findById(int id) {
		
		return ingredienteRepo.findById(id);
	}
	public Ingrediente save(Ingrediente ingrediente) {
		
		return ingredienteRepo.save(ingrediente);
	}
	public void delete(Ingrediente ingrediente) {
		
		ingredienteRepo.delete(ingrediente);
	}
}

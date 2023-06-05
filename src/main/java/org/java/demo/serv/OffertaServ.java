package org.java.demo.serv;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Offerta;
import org.java.demo.repo.OffertaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertaServ {

	@Autowired
	private OffertaRepo offertaRepo;
	
	public List<Offerta> findAll() {
		
		return offertaRepo.findAll();
	}
	public Optional<Offerta> findById(int id) {
		
		return offertaRepo.findById(id);
	}
	public Offerta save(Offerta offerta) {
		
		return offertaRepo.save(offerta);
	}
}

package org.java.demo.repo;

import java.util.List;

import org.java.demo.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepo extends JpaRepository<Pizza, Integer> {

	public List<Pizza> findByNomeContaining(String nome);
}

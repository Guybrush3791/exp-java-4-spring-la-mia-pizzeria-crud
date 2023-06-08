package org.java.demo.repo;

import org.java.demo.pojo.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepo extends JpaRepository<Ingrediente, Integer> {

}

package org.java.demo;

import java.time.LocalDate;
import java.util.List;

import org.java.demo.auth.pojo.Role;
import org.java.demo.auth.pojo.User;
import org.java.demo.auth.serv.RoleServ;
import org.java.demo.auth.serv.UserServ;
import org.java.demo.pojo.Ingrediente;
import org.java.demo.pojo.Offerta;
import org.java.demo.pojo.Pizza;
import org.java.demo.serv.IngredienteServ;
import org.java.demo.serv.OffertaServ;
import org.java.demo.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaServ pizzaServ;
	
	@Autowired
	private OffertaServ offertaServ;
	
	@Autowired
	private IngredienteServ ingredienteServ;
	
	@Autowired
	private RoleServ roleServ;
	
	@Autowired
	private UserServ userServ;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Role rUser = new Role("USER");
		Role rAdmin = new Role("ADMIN");
		
		roleServ.save(rUser);
		roleServ.save(rAdmin);
		
		String pws = new BCryptPasswordEncoder().encode("pws");
		
		User uUser = new User("user", pws, rUser);
		User uAdmin = new User("admin", pws, rAdmin);
		
		userServ.save(uUser);
		userServ.save(uAdmin);
		
		Ingrediente i1 = new Ingrediente("pomodoro");
		Ingrediente i2 = new Ingrediente("cipolla");
		Ingrediente i3 = new Ingrediente("mozzarella");
		
		ingredienteServ.save(i1);
		ingredienteServ.save(i2);
		ingredienteServ.save(i3);
		
		Pizza p1 = new Pizza("margherita", "che bella pizza", 
					"https://vitaitaliantours.com.au/wp-content/uploads/2016/03/Neapolitan-Pizza-Margherita.jpg", 
					500, i1, i2);
		Pizza p2 = new Pizza("margherita 2", "anche questa e' bella",
					"https://vitaitaliantours.com.au/wp-content/uploads/2016/03/Neapolitan-Pizza-Margherita.jpg",
					700, i1, i3);
		Pizza p3 = new Pizza("napoli", "questa e' buona",
				"https://vitaitaliantours.com.au/wp-content/uploads/2016/03/Neapolitan-Pizza-Margherita.jpg",
				950, i3);
		
		pizzaServ.save(p1);
		pizzaServ.save(p2);
		pizzaServ.save(p3);
		
		Offerta o1 = new Offerta("offerta1", 
				LocalDate.parse("2023-01-01"), 
				LocalDate.parse("2023-08-01"), 
				20, p1);
		Offerta o2 = new Offerta("offerta2", 
				LocalDate.parse("2023-03-01"), 
				LocalDate.parse("2023-07-01"), 
				50, p3);
		
		offertaServ.save(o1);
		offertaServ.save(o2);
		
		List<Offerta> offerte = offertaServ.findAll();
		System.out.println(offerte);
	}
}

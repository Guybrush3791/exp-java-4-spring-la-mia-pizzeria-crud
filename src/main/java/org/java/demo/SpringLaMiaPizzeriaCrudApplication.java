package org.java.demo;

import org.java.demo.pojo.Pizza;
import org.java.demo.serv.PizzaServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaServ pizzaServ;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Pizza p1 = new Pizza("margherita", "che bella pizza", 
					"https://vitaitaliantours.com.au/wp-content/uploads/2016/03/Neapolitan-Pizza-Margherita.jpg", 
					500);
		Pizza p2 = new Pizza("margherita 2", "anche questa e' bella",
					"https://vitaitaliantours.com.au/wp-content/uploads/2016/03/Neapolitan-Pizza-Margherita.jpg",
					700);
		Pizza p3 = new Pizza("napoli", "questa e' buona",
				"https://vitaitaliantours.com.au/wp-content/uploads/2016/03/Neapolitan-Pizza-Margherita.jpg",
				950);
		
		pizzaServ.save(p1);
		pizzaServ.save(p2);
		pizzaServ.save(p3);
	}
}

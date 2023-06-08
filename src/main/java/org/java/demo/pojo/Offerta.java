package org.java.demo.pojo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Offerta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min = 5, max = 255)
	private String titolo;

	@NotNull
	private LocalDate dataInizio;
	@NotNull
	private LocalDate dataFine;
	
	@NotNull
	@Min(1)
	@Max(100)
	private Integer percSconto;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonBackReference
	private Pizza pizza;
	
	public Offerta() { }
	public Offerta(String titolo, LocalDate dataInizio, 
				LocalDate dataFine, int percSconto, Pizza pizza) {
	
		setTitolo(titolo);
		setDataInizio(dataInizio);
		setDataFine(dataFine);
		setPercSconto(percSconto);
		setPizza(pizza);
	}
	
	@AssertTrue(message = "La data di inizio deve essere antecedente la data di fine offerta")
	private boolean isDataInizioBeforeDataFine() {
		
		return getDataInizio() != null && getDataFine() != null 
				&& getDataInizio().isBefore(getDataFine());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	public Integer getPercSconto() {
		return percSconto;
	}
	public void setPercSconto(Integer percSconto) {
		this.percSconto = percSconto;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	public Float getFinalPrice() {
		
		return (pizza.getPrezzo() / 100 * (100 - getPercSconto())) / 100f;
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getTitolo()
				+ "\nvalidita': " + getDataInizio() + " ~ " + getDataFine()
				+ "\nsconto: " + getPercSconto() + "%"
				+ "\npizza: " + getPizza().getNome();
	}
}

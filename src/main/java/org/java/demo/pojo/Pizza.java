package org.java.demo.pojo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min = 3, message = "Il nome deve essere di almeno 3 caratteri")
	private String nome;
	@NotBlank
	@Size(min = 5)
	private String descrizione;
	
	@NotBlank(message = "Url della foto non puo' essere vuoto")
	private String fotoUrl;
	@Min(0)
	@NotNull
	private Integer prezzo;
	
	@OneToMany(mappedBy = "pizza")
	private List<Offerta> offerte;
	
	public Pizza() { }
	public Pizza(String nome, String descrizione, String fotoUrl, int prezzo) {
		
		setNome(nome);
		setDescrizione(descrizione);
		setFotoUrl(fotoUrl);
		setPrezzo(prezzo);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getFotoUrl() {
		return fotoUrl;
	}
	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}
	public Integer getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}
	public List<Offerta> getOfferte() {
		return offerte;
	}
	public void setOfferte(List<Offerta> offerte) {
		this.offerte = offerte;
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getNome() + " - " + getPrezzo()
			+ "\n" + getDescrizione()
			+ "\nfoto url: " + getFotoUrl();
	}
}

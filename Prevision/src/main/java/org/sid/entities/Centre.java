package org.sid.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Centre implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	private String localite;
	@OneToMany(mappedBy = "centre")
	private List<Bureau> bureaux;
	
	
	public Centre() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getLocalite() {
		return localite;
	}


	public void setLocalite(String localite) {
		this.localite = localite;
	}


	public List<Bureau> getBureaux() {
		return bureaux;
	}


	public void setBureaux(List<Bureau> bureaux) {
		this.bureaux = bureaux;
	}
	
	
	
	
	

}

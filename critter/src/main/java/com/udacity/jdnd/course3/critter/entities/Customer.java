package com.udacity.jdnd.course3.critter.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Customer extends User{

	private String notes;
	 
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Pet> pets;

	public Customer() {
	}

	public Customer(String notes, List<Pet> pets) {
		this.notes = notes;
		this.pets = pets;
	}

	public String getNotes()  {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
	
	public void addPet(Pet pet){
		this.pets.add(pet);
	}
}





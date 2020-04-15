package com.udacity.jdnd.course3.critter.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate date;
	
	@Enumerated
	@ElementCollection(targetClass = EmployeeSkill.class)
	private Set<EmployeeSkill> skills;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	private List<Employee> employees;

	@ManyToMany(cascade = { CascadeType.ALL })
	private List<Pet> pets;

	public Schedule(){
	}
			
	public Schedule(Long id, LocalDate date, Set<EmployeeSkill> skills, List<Employee> employees, List<Pet> pets) {
		this.id = id;
		this.date = date;
		this.skills = skills;
		this.employees = employees;
		this.pets = pets;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Set<EmployeeSkill> getSkills() {
		return skills;
	}

	public void setSkills(Set<EmployeeSkill> skills) {
		this.skills = skills;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
}
package com.udacity.jdnd.course3.critter.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.exceptions.CustomerNotFoundExpetion;
import com.udacity.jdnd.course3.critter.exceptions.PetNotFoundExpetion;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;

@Service
@Transactional
public class PetService {

	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Pet save(Pet pet, long customerId) {
		Customer customer = this.customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundExpetion());
		pet.setCustomer(customer);
		return this.petRepository.save(pet);
	}
 
	public List<Pet> findAll() {
		return this.petRepository.findAll();
	}

	public Pet getPetById(long id) {
		return this.petRepository.findById(id).
				orElseThrow(() -> new PetNotFoundExpetion());
	}

	public List<Pet>  getPetsByCustomer(long customerId) {
		return this.petRepository.findAllByCustomerId(customerId);
	}

}

package com.udacity.jdnd.course3.critter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer save(Customer customer) {
		return this.customerRepository.save(customer);
	}


	public List<Customer> findAll() {
		return this.customerRepository.findAll();
	}


	public Customer findByPetId(long petId) {
		return this.customerRepository.findByPetsId(petId);
	}

}

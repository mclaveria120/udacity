package com.udacity.jdnd.course3.critter.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;

@Service
@Transactional
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	public Schedule save(Schedule schedule) {
		return this.scheduleRepository.save(schedule);
	}

	public List<Schedule> findAll() {
		return this.scheduleRepository.findAll();
	}

	public List<Schedule> findAllForPet(long petId) {
		return  this.scheduleRepository.findAllByPetsId(petId);
	}

	public List<Schedule> findAllForEmployee(long employeeId) {
		return this.scheduleRepository.findAllByEmployeesId(employeeId);
	}

	public List<Schedule> findAllForCustomer(long customerId) {
		return this.scheduleRepository.findAllByPetsCustomerId(customerId);
	}

}

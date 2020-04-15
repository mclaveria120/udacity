package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entities.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	public List<Schedule> findAllByPetsId(long petId);

	public List<Schedule> findAllByEmployeesId(long employeeId);

	public List<Schedule> findAllByPetsCustomerId(long customerId);
	
}

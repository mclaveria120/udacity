package com.udacity.jdnd.course3.critter.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.EmployeeSkill;
import com.udacity.jdnd.course3.critter.exceptions.EmployeeNotFoundExpetion;
import com.udacity.jdnd.course3.critter.repository.*;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee save(Employee employee) {
		return this.employeeRepository.save(employee);
	}
	
	public Employee getById(long id) {
		return this.employeeRepository.findById(id).
				orElseThrow(() -> new EmployeeNotFoundExpetion());
	}

	public void updateEmployeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
		 Employee employee = this.getById(employeeId);
		 employee.setDaysAvailable(daysAvailable);
		 this.employeeRepository.save(employee);
	} 

	public List<Employee> findEmployeeForService(LocalDate date, Set<EmployeeSkill> skills) {
		List<Employee> employees = this.employeeRepository.findAllByDaysAvailable(date.getDayOfWeek());
		return employees.stream()
				.filter(x -> x.getSkills()
				.containsAll(skills))
				.collect(Collectors.toList());
	}
}

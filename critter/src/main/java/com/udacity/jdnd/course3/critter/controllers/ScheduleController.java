package com.udacity.jdnd.course3.critter.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.services.ScheduleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private PetService petService;

	@Autowired
	private EmployeeService employeeService;
	
	
    @PostMapping 
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
    	 Schedule schedule =  this.scheduleService.save(convertScheduleDTOToSchedule(scheduleDTO));
         return convertScheduleToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
    	 return this.scheduleService.findAll()
    			 .stream().map(x->convertScheduleToScheduleDTO(x))
    			 .collect(Collectors.toList()); 
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return  this.scheduleService.findAllForPet(petId)
	        		.stream().map(x->convertScheduleToScheduleDTO(x))
	        		.collect(Collectors.toList()); 
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return this.scheduleService.findAllForEmployee(employeeId)
					.stream().map(x->convertScheduleToScheduleDTO(x))
					.collect(Collectors.toList()); 
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
    	 return this.scheduleService.findAllForCustomer(customerId)
					.stream().map(x->convertScheduleToScheduleDTO(x))
					.collect(Collectors.toList()); 
    }
    
    private  ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
    	ScheduleDTO scheduleDTO = new ScheduleDTO();
    		BeanUtils.copyProperties(schedule, scheduleDTO);
    		scheduleDTO.setActivities(schedule.getSkills());
    	if(schedule.getPets()!=null) {
    		scheduleDTO.setPetIds( 
    				schedule.getPets()
            			.stream().map(x -> x.getId())
            			.collect(Collectors.toList()));	
    	}
    	if(schedule.getEmployees()!=null) {
    		scheduleDTO.setEmployeeIds( 
    				schedule.getEmployees()
    				.stream().map(x -> x.getId())
    				.collect(Collectors.toList()));	
    	}
    	return scheduleDTO;
    }
    
    private  Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO){
    	Schedule schedule= new Schedule();
    	schedule.setDate(scheduleDTO.getDate());
    	schedule.setSkills(scheduleDTO.getActivities());
    	
    	if(scheduleDTO.getPetIds()!=null) {
    		schedule.setPets(scheduleDTO.getPetIds()
    			.stream().map(petId -> this.petService.getPetById(petId))
    			.collect(Collectors.toList()));	
            			
    	}
    	if(scheduleDTO.getEmployeeIds()!=null) {
    		schedule.setEmployees(
    				scheduleDTO.getEmployeeIds()
    					.stream().map(employeeId ->this.employeeService.getById(employeeId))
    					.collect(Collectors.toList()));	
    		
    	}
    	return schedule;
    }
}

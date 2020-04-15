package com.udacity.jdnd.course3.critter.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private EmployeeService employeeService;
	
	
    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
       Customer customer =  this.customerService.save(convertCustomerDTOToCustomer(customerDTO));
       return convertCustomerToCustomerDTO(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
    	 List<Customer> customers = this.customerService.findAll();
		return customers.stream().map(x->convertCustomerToCustomerDTO(x)).collect(Collectors.toList());
    }
    
    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
    	  Customer customer =  this.customerService.findByPetId(petId);
    	return convertCustomerToCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
    	 Employee employee =  this.employeeService.save(convertEmployeeDTOToEmployee(employeeDTO));
         return convertEmployeeToEmployeeDTO(employee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
    	  return convertEmployeeToEmployeeDTO(this.employeeService.getById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
    	this.employeeService.updateEmployeAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
    	 return this.employeeService.findEmployeeForService(employeeDTO.getDate(),employeeDTO.getSkills())
    			 	.stream().map(x ->convertEmployeeToEmployeeDTO(x))
    			 	.collect(Collectors.toList());
    }

     
    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
    	EmployeeDTO employeeDTO = new EmployeeDTO(); 
    	BeanUtils.copyProperties(employee, employeeDTO);
    	return employeeDTO;
    }
    
    private  Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO){
    	Employee employee= new Employee();
    	BeanUtils.copyProperties(employeeDTO, employee);
    	employee.setSkills(employeeDTO.getSkills());
    	return employee;
    }
    private  CustomerDTO convertCustomerToCustomerDTO(Customer customer){
    	CustomerDTO customerDTO = new CustomerDTO();
    	BeanUtils.copyProperties(customer, customerDTO);
    	if(customer.getPets()!=null) {
    		customerDTO.setPetIds( 
        			customer.getPets()
            			.stream().map(x -> x.getId())
            			.collect(Collectors.toList()));	
    	}
    	return customerDTO;
    }
    
    private  Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO){
    	Customer customer= new Customer();
    	BeanUtils.copyProperties(customerDTO, customer);
    	return customer;
    }
}

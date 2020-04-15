package com.udacity.jdnd.course3.critter.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.services.PetService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/pet")
public class PetController {

	@Autowired
	private PetService petService;
	
    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
    	Pet pet = this.petService.save(convertPetDTOToPet(petDTO), petDTO.getOwnerId());
    	return convertPetToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
       return convertPetToPetDTO(this.petService.getPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
       return this.petService.findAll()
    		   .stream().map(PetController::convertPetToPetDTO)
    		   .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
    	return this.petService.getPetsByCustomer(ownerId)
    			.stream().map(PetController::convertPetToPetDTO)
    			.collect(Collectors.toList());
    } 
    
    private static PetDTO convertPetToPetDTO(Pet pet){
    	PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }
     
    private static Pet convertPetDTOToPet(PetDTO petDTO){
    	Pet pet = new Pet();
    	BeanUtils.copyProperties(petDTO, pet);
    	return pet;
    }
}

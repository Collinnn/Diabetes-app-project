package dtu.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dtu.model.Doctor;
import dtu.repositories.DoctorRepository;

@RestController @CrossOrigin @RequestMapping("/api/v1")
public class DoctorController {
	
	@Autowired
	private DoctorRepository doctorRepository;
	//Get all doctors
	@GetMapping("/doctor")
	public ResponseEntity<List<Doctor>> getAll(@RequestParam(required=false)String firstName){
		List<Doctor> doctor = new ArrayList<Doctor>();
		if(firstName==null) {
			doctorRepository.findAll().forEach(doctor::add);
		}else {
			doctorRepository.findByFirstNameContaining(firstName).forEach(doctor::add);
		}
		if(doctor.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(doctor,HttpStatus.OK);
	}
	
	//Find doctor by ID
	@GetMapping("/Doctor/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable(value = "id") int id){
		Doctor doctor = doctorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No doctor found with Id: " + id));
		return new ResponseEntity<>(doctor,HttpStatus.OK);
	}
	//Post a new Doctor
	@PostMapping("/doctor")
	public ResponseEntity<Doctor>createDoctor(@RequestBody Doctor doctor){
		return ResponseEntity.ok(doctorRepository.save(doctor));
	}
	//Update a doctor with new Data. 
	@PutMapping("/doctor{id}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable("id") int id,@RequestBody Doctor doctor){
		Doctor _doctor = doctorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No doctor found with íd" + id));
		return new ResponseEntity<>(_doctor,HttpStatus.CREATED);
	}
	//Deletes doctor with id
	@DeleteMapping("/doctor/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		doctorRepository.deleteById(id);;
		return ResponseEntity.noContent().build();
	}
	
}

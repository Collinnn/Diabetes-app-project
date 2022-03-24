package dtu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dtu.model.Patient;
import dtu.repositories.PatientRepository;

@Controller @CrossOrigin
public class PatientController {
	
	@Autowired
	private PatientRepository repository;
	
	@GetMapping("/api/v1/patient")
	public ResponseEntity<List<Patient>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping("/api/v1/patient")
	public ResponseEntity<Patient> create(@RequestBody Patient patient){
		return ResponseEntity.ok(repository.save(patient));
	}
	
	@DeleteMapping("/api/v1/patient/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		repository.deleteById(id);;
		return ResponseEntity.noContent().build();
	}
	
	
}

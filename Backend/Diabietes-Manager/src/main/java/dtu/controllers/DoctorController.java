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

import dtu.model.Doctor;
import dtu.repositories.DoctorRepository;

@Controller @CrossOrigin
public class DoctorController {
	
	@Autowired
	private DoctorRepository repository;
	
	@GetMapping("/api/v1/doctor")
	public ResponseEntity<List<Doctor>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping("/api/v1/doctor")
	public ResponseEntity<Doctor>create(@RequestBody Doctor doctor){
		return ResponseEntity.ok(repository.save(doctor));
	}
	
	@DeleteMapping("/api/v1/doctor/{doctorid}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		repository.deleteById(id);;
		return ResponseEntity.noContent().build();
	}
}
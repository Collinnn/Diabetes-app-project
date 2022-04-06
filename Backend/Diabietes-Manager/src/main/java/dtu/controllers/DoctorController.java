package dtu.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dtu.model.Doctor;
import dtu.repositories.DoctorRepository;

@Controller @CrossOrigin @RequestMapping("/api/v1")
public class DoctorController {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@GetMapping("/doctor")
	public ResponseEntity<List<Doctor>> getAll(@RequestParam(required=false)String firstName){
		List<Doctor> doctor = new ArrayList<Doctor>();
		if(firstName==null) {
			doctorRepository.findAll().forEach(doctor::add);
		}else {
			doctorRepository.findByfirstNameContaining(firstName).forEach(doctor::add);
		}
		return ResponseEntity.ok(doctorRepository.findAll());
	}
	
	@PostMapping("/api/v1/doctor")
	public ResponseEntity<Doctor>create(@RequestBody Doctor doctor){
		return ResponseEntity.ok(doctorRepository.save(doctor));
	}
	
	@DeleteMapping("/api/v1/doctor/{doctorid}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		doctorRepository.deleteById(id);;
		return ResponseEntity.noContent().build();
	}
}

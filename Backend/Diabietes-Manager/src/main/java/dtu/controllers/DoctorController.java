package dtu.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dtu.model.Doctor;
import dtu.repositories.DoctorRepository;

@RestController @CrossOrigin @RequestMapping("/api/v1")
public class DoctorController {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@GetMapping("/doctors")
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		return ResponseEntity.ok(doctorRepository.findAll());
	}
	
	@PostMapping("/doctors")
	public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
		return ResponseEntity.ok(doctorRepository.save(doctor));
	}
	
	@GetMapping("/doctors/{doctorId}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable Integer doctorId) {
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		if (doctor.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(doctor.get());
	}
	
	@DeleteMapping("/doctors/{doctorId}")
	public ResponseEntity<?> deleteDoctorById(@PathVariable Integer doctorId) {
		doctorRepository.deleteById(doctorId);
		return ResponseEntity.noContent().build();
	}
	
	// PutMapping // Change doctor name
	
	
}

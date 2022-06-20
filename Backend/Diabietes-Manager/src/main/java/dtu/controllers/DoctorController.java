package dtu.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dtu.model.Doctor;
import dtu.model.Patient;
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
		if(doctor.getPatients()==null) {
			doctor.setPatients(new ArrayList<Patient>());
		}
		return ResponseEntity.ok(doctorRepository.save(doctor));
	}
	
	@GetMapping("/doctors/{doctorId}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable int doctorId) {
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		if (doctor.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(doctor.get());
	}
	
	@PutMapping("/doctors/{doctorId}")
	public ResponseEntity<Doctor> updateDoctorDetails(@PathVariable int doctorId, @RequestBody Doctor updatedDoctorDetails) {
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		if (doctor.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		doctor.get().setFirstName(updatedDoctorDetails.getFirstName());
		doctor.get().setLastName(updatedDoctorDetails.getLastName());
		doctor.get().setPassword(updatedDoctorDetails.getPassword());
		return ResponseEntity.ok(doctor.get());
	}	
	
	@DeleteMapping("/doctors/{doctorId}")
	public ResponseEntity<?> deleteDoctorById(@PathVariable int doctorId) {
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		if (doctor.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		for (Patient patient : doctor.get().getPatients()) {
			patient.setDoctor(null);
		}
		doctorRepository.deleteById(doctorId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/doctors/{doctorId}/patients")
	public ResponseEntity<List<Patient>> getPatientsFromDoctor(@PathVariable int doctorId) {
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		if (doctor.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(doctor.get().getPatients());
	}
	
}

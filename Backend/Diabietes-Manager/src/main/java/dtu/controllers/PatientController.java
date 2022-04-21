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
import dtu.model.Patient;
import dtu.repositories.DoctorRepository;
import dtu.repositories.PatientRepository;

@RestController @CrossOrigin @RequestMapping("/api/v1")
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	
	// Gets all Patients associated with a doctor
	@GetMapping("/doctors/{doctorId}/patients")
	public ResponseEntity<List<Patient>> getAll(@PathVariable int doctorId) {
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		if (doctor.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(doctor.get().getPatients());
	}
	
	// Post new patient to repository
	@PostMapping("/doctors/{doctorId}/patients")
	public ResponseEntity<Patient> create(@RequestBody Patient patient, @PathVariable int doctorId) {
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		if (doctor.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		doctor.get().addPatient(patient);
		return ResponseEntity.ok(patientRepository.save(patient));
	}
	
	@DeleteMapping("/doctors/{doctorId}/patients/{patientId}")
	public ResponseEntity<?> delete(@PathVariable int doctorId, @PathVariable int patientId) {
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		if (doctor.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Optional<Patient> patient = patientRepository.findById(patientId);
		if (patient.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		doctor.get().removePatient(patient.get());
		doctorRepository.save(doctor.get());
		return ResponseEntity.noContent().build();
	}
}

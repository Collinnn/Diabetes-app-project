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
import org.springframework.web.bind.annotation.PutMapping;
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
	
	//get mapping for all patients
	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> getAllPatients(){
		return ResponseEntity.ok(patientRepository.findAll());
	}

	//get mapping for single patient
	@GetMapping("/patients/{patientId}")
	public ResponseEntity<Patient> getPatientById(@PathVariable int patientId){
		Optional<Patient> patient = patientRepository.findById(patientId);
		if (patient.isEmpty()){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(patient.get());
	}
	
	@PostMapping("/patients")
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
		Doctor doctor = patient.getDoctor();
		if (doctor != null && doctorRepository.existsById(doctor.getId())) {
			doctor = doctorRepository.findById(doctor.getId()).get();
			patient.setDoctor(doctor);
			doctor.addPatient(patient);
		}
		return ResponseEntity.ok(patientRepository.save(patient));
	}
	

	@PutMapping("/patients/{patientId}")
	public ResponseEntity<Patient> updatePatient(@PathVariable int patientId, @RequestBody Patient patient){
		Optional<Patient> oPatient = patientRepository.findById(patientId);
		if(oPatient.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		oPatient.get().setFirstName(patient.getFirstName());
		oPatient.get().setLastName(patient.getLastName());
		oPatient.get().setPassword(patient.getPassword());
		oPatient.get().setDateOfBirth(patient.getDateOfBirth());
		oPatient.get().setDoctor(patient.getDoctor());
		return ResponseEntity.ok(patientRepository.save(oPatient.get()));
	}

	@DeleteMapping("/patients/{patientId}")
	public ResponseEntity<?> deletePatientById(@PathVariable int patientId){
		patientRepository.deleteById(patientId);
		return ResponseEntity.noContent().build();
	}
	
}

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dtu.model.Patient;
import dtu.repositories.DoctorRepository;
import dtu.repositories.PatientRepository;

@RestController @CrossOrigin @RequestMapping("/api/v1")
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	
	//Gets all Patients
	@GetMapping("/patient")
	public ResponseEntity<List<Patient>> getAll(){
		List<Patient> patient = new ArrayList<Patient>();
		patientRepository.findAll().forEach(patient::add);
		if (patient.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(patient,HttpStatus.OK);
		}
	}
	//Gets all patients of a specific doctor by id
	@GetMapping("/Doctor/{Doctorid}/patient")
	public ResponseEntity<List<Patient>> getAllPatientsByDoctorId(@PathVariable(value = "DoctorId")int doctorId){
		if(!doctorRepository.existsById(doctorId)) {
			throw new RuntimeException("Not found Doctor with Id: " + doctorId);
		}
		List<Patient> patients = patientRepository.findPatientsByDoctorId(doctorId);
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}
	//Gets patient by id
	@GetMapping("patient/{id}")
	public ResponseEntity<Patient> getPatientbyId(@PathVariable(value = "id")int id){
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Not found patient with Id: " + id));
		return new ResponseEntity<>(patient,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/api/v1/patient")
	public ResponseEntity<Patient> create(@RequestBody Patient patient){
		return ResponseEntity.ok(patientRepository.save(patient));
	}
	
	@DeleteMapping("/api/v1/patient/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		patientRepository.deleteById(id);;
		return ResponseEntity.noContent().build();
	}
	
	
}

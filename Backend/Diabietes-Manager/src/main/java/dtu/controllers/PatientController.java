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
	
	//Gets all Patients
	@GetMapping("/Patient")
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
	@GetMapping("/Doctor/{DoctorId}/Patient")
	public ResponseEntity<List<Patient>> getAllPatientsByDoctorId(@PathVariable(value = "DoctorId")int doctorId){
		if(!doctorRepository.existsById(doctorId)) {
			throw new RuntimeException("Not found Doctor with id: " + doctorId);
		}
		List<Patient> patients = patientRepository.findPatientsByDoctorId(doctorId);
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}
	//Gets patient by id
	@GetMapping("Patient/{PatientId}")
	public ResponseEntity<Patient> getPatientbyId(@PathVariable(value = "id")int id){
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Not found patient with Id: " + id));
		return new ResponseEntity<>(patient,HttpStatus.OK);
	}
	//Gets all doctors by patientID
	@GetMapping("Patient/{PatientId}/Doctor")
	public ResponseEntity<List<Doctor>> getAllDoctorsByPatientId(@PathVariable(value="PatientId")int patientId){
		if(!patientRepository.existsById(patientId)) {
			throw new RuntimeException("Not found a Patient with id: " + patientId);
		}
		List<Doctor> doctors = doctorRepository.findDoctorsByPatientId(patientId);
		return new ResponseEntity<>(doctors,HttpStatus.OK);
		
	}
	
	@PostMapping("/patient")
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patientRequest){
		Patient patient = patientRepository
				.save(new Patient(patientRequest.getFirstName(),patientRequest.getLastName(),patientRequest.getPassword()));
		return new ResponseEntity<>(patient,HttpStatus.CREATED);
	}
	
	
	//Update existing doctor
	@PutMapping("/patients/{id}")
	public ResponseEntity<Doctor> updateTag(@PathVariable("id") int id, @RequestBody Patient doctorRequest){
		Doctor patient = doctorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Not found a Doctor with id:" + id));
		patient.setFirstName(doctorRequest.getFirstName());
		patient.setLastName(doctorRequest.getLastName());
		patient.setPassword(doctorRequest.getPassword());
		return new ResponseEntity<>(doctorRepository.save(patient),HttpStatus.OK);
		
			
	}
	//Deletes a doctors patient with an id of the doctor and the patient
	  @DeleteMapping("/Doctor/{doctorId}/patient/{id}")
	  public ResponseEntity<HttpStatus> deletePatientFromDoctor(@PathVariable(value = "doctorId") int doctorId, @PathVariable(value = "patientId") int patientId) {
	    Doctor doctor = doctorRepository.findById(doctorId)
	        .orElseThrow(() -> new RuntimeException("Not found Tutorial with id = " + doctorId));
	   
	    
	    doctor.removePatient(patientId);
	    doctorRepository.save(doctor);
	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	
	  
	@DeleteMapping("patient/{id}")
	public ResponseEntity<HttpStatus> deletePatient(@PathVariable int id){
		patientRepository.deleteById(id);;
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}

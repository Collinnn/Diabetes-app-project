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
	public ResponseEntity<Patient> getPatientById(@PathVariable int patientid){
		Optional<Patient> patient = patientRepository.findById(patientid);
		if (patient.isEmpty()){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(patient.get());
	}
	@PostMapping("/patients")
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
		int doctorId = patient.getDoctor().getId();
		String doctorFirstName = patient.getDoctor().getFirstName();
		String doctorLastName = patient.getDoctor().getLastName();
		if(doctorRepository.existsById(doctorId) || doctorRepository.getById(doctorId).getFirstName() == doctorFirstName 
				|| doctorRepository.getById(doctorId).getLastName() == doctorLastName) {
			Doctor doc = doctorRepository.getById(doctorId);
			doc.addPatient(patient);
			doctorRepository.save(doc);
			return ResponseEntity.ok(patientRepository.save(patient));
		}
		return ResponseEntity.notFound().build();
		
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
		oPatient.get().getDoctor().setFirstName(patient.getDoctor().getFirstName());
		oPatient.get().getDoctor().setLastName(patient.getDoctor().getLastName());
		oPatient.get().getDoctor().setPassword(patient.getDoctor().getPassword());
		return ResponseEntity.ok(patientRepository.save(oPatient.get()));
	}

	@DeleteMapping("/patients/{patientId}")
	public ResponseEntity<?> deletePatientById(@PathVariable int patientId){
		patientRepository.deleteById(patientId);
		return ResponseEntity.noContent().build();
	}

	// Gets all Patients associated with a doctor
	@GetMapping("/doctors/{doctorId}/patients")
	public ResponseEntity<List<Patient>> getAllDoctors(@PathVariable int doctorId) {
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		if (doctor.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(doctor.get().getPatients());
	}
	
}

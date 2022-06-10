package dtu.controllers;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dtu.model.Measurement;
import dtu.model.MeasurementId;
import dtu.model.Patient;
import dtu.repositories.MeasurementRepository;
import dtu.repositories.PatientRepository;

@RestController @CrossOrigin @RequestMapping("/api/v1")
public class MeasurementController {

	@Autowired 
	MeasurementRepository measurementRepo;
	@Autowired 
	PatientRepository patientRepo;
	
	@PostMapping("/patients/{patientId}/measurements")
	public ResponseEntity<Measurement> getMeasurementsFromPatient(@PathVariable int patientId, @RequestBody Measurement measurement) {
		Optional<Patient> p = patientRepo.findById(patientId);
		if(p.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		MeasurementId measurementId = new MeasurementId(ts,p.get().getId());
		measurement.setMeasurementId(measurementId);
		return ResponseEntity.ok(measurementRepo.save(measurement));
	}
	
	
	
}

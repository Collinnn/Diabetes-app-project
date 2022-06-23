/* Responsible author: Simon Poulsen 
 * Contributors:
 */

package dtu.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/patients/{patientId}/measurements")
	public ResponseEntity<List<Measurement>> getMeasurementsFromPatient(@PathVariable int patientId) {
		Optional<Patient> p = patientRepo.findById(patientId);
		if(p.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(p.get().getMeasurements());
	}
	
	@PostMapping("/patients/{patientId}/measurements")
	public ResponseEntity<Measurement> addMeasurementToPatient(@PathVariable int patientId, @RequestBody Measurement measurement) {
		Optional<Patient> p = patientRepo.findById(patientId);
		if(p.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		MeasurementId measurementId = new MeasurementId(ts,p.get().getId());
		measurement.setMeasurementId(measurementId);
		p.get().addMeasurement(measurement);
		return ResponseEntity.ok(measurementRepo.save(measurement));
	}
	
	@PutMapping("/patients/{patientId}/measurements/{timestamp}")
	public ResponseEntity<Measurement> updateMeasurement(@PathVariable int patientId, 
			@RequestBody Measurement measurement, @PathVariable Timestamp timestamp) {
		Optional<Patient> p = patientRepo.findById(patientId);
		if(p.isEmpty()) {
			return ResponseEntity.notFound().build();
		} 
		MeasurementId measurementId = new MeasurementId(timestamp, patientId);
		Optional<Measurement> m = measurementRepo.findById(measurementId);
		if(m.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		m.get().setBasal(measurement.getBasal());
		m.get().setBolus(measurement.getBolus());
		m.get().setCarbohydrates(measurement.getCarbohydrates());
		p.get().addMeasurement(m.get());
		return ResponseEntity.ok(measurementRepo.save(m.get()));
	}
	
	
}

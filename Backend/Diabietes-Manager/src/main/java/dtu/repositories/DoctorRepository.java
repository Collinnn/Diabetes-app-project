package dtu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dtu.model.Doctor;
import dtu.model.Patient;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
	
	List<Doctor> findByFirstNameContaining(String firstName);
	List<Doctor> findDoctorsByPatientId(int patientId);
	List<Patient> findPatientByDoctorId(int doctorId);
}

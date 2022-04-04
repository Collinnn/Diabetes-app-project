package dtu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dtu.model.Doctor;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
	List<Doctor> findDoctorsByPatientId(int patientId);
}

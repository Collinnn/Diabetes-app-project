package dtu.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dtu.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient,Integer> {
	List<Patient> findAll();
}

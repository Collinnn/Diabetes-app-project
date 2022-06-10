package dtu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dtu.model.Measurement;
import dtu.model.MeasurementId;

public interface MeasurementRepository extends JpaRepository<Measurement,MeasurementId> {
}

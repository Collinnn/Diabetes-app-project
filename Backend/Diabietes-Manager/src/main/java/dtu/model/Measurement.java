package dtu.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Measurement {
	
	@EmbeddedId
	@Column
	private MeasurementId measurementId;
	@Column(name = "glucoselevel")
	private double glucoseLevel;
	@Column(name = "bolus")
	private double bolus;
	@Column(name = "basal")
	private double basal;
	@Column(name = "carbohydrates")
	private double carbohydrates;
	@MapsId("id")
	@JoinColumn(name = "patientId", referencedColumnName = "id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Patient patient;
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public MeasurementId getMeasurementId() {
		return measurementId;
	}
	public void setMeasurementId(MeasurementId measurementId) {
		this.measurementId = measurementId;
	}
	public double getGlucoseLevel() {
		return glucoseLevel;
	}
	public void setGlucoseLevel(double glucoseLevel) {
		this.glucoseLevel = glucoseLevel;
	}
	public double getBolus() {
		return bolus;
	}
	public void setBolus(double bolus) {
		this.bolus = bolus;
	}
	public double getBasal() {
		return basal;
	}
	public void setBasal(double basal) {
		this.basal = basal;
	}
	public double getCarbohydrates() {
		return carbohydrates;
	}
	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}
	
}

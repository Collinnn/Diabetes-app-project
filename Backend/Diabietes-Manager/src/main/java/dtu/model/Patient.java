package dtu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Patient {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "password")
	private String password;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "dateOfBirth")
	private String dateOfBirth;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Doctor doctor;
	@OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Measurement> measurements;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	

	public List<Measurement> getMeasurements() {
		return measurements;
	}
	
	public void setMeasurements(ArrayList<Measurement> measurements) {
		this.measurements = measurements;	
	}

	public void addMeasurement(Measurement measurement) {
		measurements.add(measurement);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Patient) {
			return ((Patient) obj).id == this.id;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}


		
}

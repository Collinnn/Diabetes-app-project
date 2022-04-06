package dtu.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name = "Doctor")
public class Doctor {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "password")
	private String password;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@ManyToMany(fetch = FetchType.LAZY,
			cascade= {
				CascadeType.PERSIST,
				CascadeType.MERGE
			})
	@JoinTable(name = "patient-doctor",
    joinColumns = { @JoinColumn(name = "Doctor_id") },
    inverseJoinColumns = { @JoinColumn(name = "patient_id") })
	private List<Patient> patients;
	
	public Doctor() {
		
	}
	public Doctor(String password, String firstName, String lastName) {
		this.password=password;
		this.firstName=firstName;
		this.lastName=lastName;
	}
	
	
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
	public List<Patient> getPatients(){
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	public void addPatient(Patient patient) {
		this.patients.add(patient);
	}
	public void removePatient(Patient patient) {
		this.patients.remove(patient);
	}
	
	
}

/* Responsible author: Jacob Martens
 * Contributors:
 */

package dtu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Doctor {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String password;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@OneToMany(
			mappedBy = "doctor",
			cascade = CascadeType.PERSIST
			)
	@JsonManagedReference
	private List<Patient> patients;

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
	
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}
	
	public void addPatient(Patient patient) {
		this.patients.add(patient);
	}
	
	public void removePatient(Patient patient) {
		this.patients.remove(patient);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Doctor) {
			return ((Doctor) obj).id == this.id;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}
	
}

package dtu.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonFormat;

@Embeddable
public class MeasurementId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6042687706100524982L;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String timestamp;
	private int patientId;
	
	public MeasurementId(Timestamp timestamp, int patientId) {
		super();
		this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:00").format(timestamp);
		this.patientId = patientId;
	}
	
	public MeasurementId() {
		
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:00").format(timestamp);
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + patientId;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeasurementId other = (MeasurementId) obj;
		if (patientId != other.patientId)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}
		
}

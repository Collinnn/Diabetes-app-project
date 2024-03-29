/* Responsible author: Tobias Collin 
 * Contributors: Simon Poulsen
 */

package dtu.components;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import dtu.model.Measurement;
import dtu.model.MeasurementId;
import dtu.model.Patient;


@Component
public class PulseMeasurement {
	
	private int i = 0;

	@Scheduled(cron = "0 */5 * * * *")
	public void postMeasurements() throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		MeasurementId measurementId;
		String url;
		HttpEntity<Measurement> request = new HttpEntity<>(new Measurement());

		List<Patient> patientList = patientList();
		for(Patient patient : patientList){
			int id = patient.getId();
			url = "http://localhost:8080/api/v1/patients/" + id + "/measurements";
			measurementId = new MeasurementId(ts, id);
			String[] listOfGlucoseLevels = readLine(System.getProperty("user.dir") + "/example_diabetes_data/measurements.csv",id);
			double glucoseLevel = token(listOfGlucoseLevels,i);
			
			request.getBody().setMeasurementId(measurementId);
			request.getBody().setGlucoseLevel(glucoseLevel);
			request.getBody().setBasal(0.0);
			request.getBody().setBolus(0.0);
			request.getBody().setCarbohydrates(0.0);
			restTemplate.postForObject(url, request, Measurement.class);
		}
		this.i++;
			
	}
	
	public List<Patient> patientList(){
		RestTemplate restTemplate = new RestTemplate();
		String url ="http://localhost:8080/api/v1/patients";
		ResponseEntity<Patient[]> response = 
				restTemplate.getForEntity(url,Patient[].class);
		Patient[] patient = response.getBody();
		return Arrays.asList(patient);
		
	}

	public String[] readLine(String filename, int id) throws IOException {
		File file = new File(filename);
		List<String> listOfLines = Files.readAllLines(file.toPath());
		String patientData = listOfLines.get(id-1);
		String[] measurements = patientData.split(",");
		return measurements;
	}

	public double token(String[] measurements, int index) {
		return Double.parseDouble(measurements[index]);
	}
	

}

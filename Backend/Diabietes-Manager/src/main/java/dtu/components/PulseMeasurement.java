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

	@Scheduled(cron = "* */5 * * * *")
	public void postMeasurements() throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		
		List<Patient> patientList = patientList();
			for(int j =0;j<patientList.size();j++) {
				HttpEntity<Measurement> request = new HttpEntity<>(new Measurement());
				int id = patientList.get(j).getId();
				String url = "http://localhost:8080/api/v1/patients/" + id + "/measurements";
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				MeasurementId measurementId = new MeasurementId(ts,id);
				String[] listOfGlucoseLevels = readLine(System.getProperty("user.dir") + "/example_diabetes_data/measurements.csv",id);
				double glucoseLevel = token(listOfGlucoseLevels,i);
				
				request.getBody().setMeasurementId(measurementId);
				request.getBody().setGlucoseLevel(glucoseLevel);

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
	
	
//	public void readDataLine(String fileName, int id) {
//		try {
//			File file = new File(fileName);
//			List<String> listOfLines = Files.readAllLines(file.toPath());
//			String patientData = listOfLines.get(id-1);
//			//Spilts a single line from a patient into datapoints
//			String[] measurments = patientData.split(",");
//			System.out.println(measurments[this.i]);
//			this.i++;
//			
//			
//			
//		}catch(Exception error) {
//			error.printStackTrace();
//		}
//	}
	
	
	
	
	/*
	List<Patient> patientList = patientList();
	for(int i=0;i<patientList.size();i++) {
		HttpEntity<Measurement> request = new HttpEntity<>(new Measurement());
		int id = patientList.get(i).getId();
		String url = "http://localhost:8080/api/v1/patients/" + id + "/measurements";
		
		
					request.getBody().setMeasurementId(measurementId);
			double value = 0.0;
			request.getBody().setGlucoseLevel(value);

		
	}
	//NEEDED TO MAKE A TIMESTAMP
	Timestamp ts = new Timestamp(System.currentTimeMillis());
	System.out.println(ts);
	MeasurementId measurementId = new MeasurementId(ts,patientId);
	
	request.getBody().setMeasurementId(measurementId);
	System.out.println(request.getBody().getMeasurementId().getTimestamp());
	
	restTemplate.postForObject(url, request, Measurement.class);
	*/

}

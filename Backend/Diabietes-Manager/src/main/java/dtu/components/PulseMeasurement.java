package dtu.components;

import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import dtu.model.Measurement;
import dtu.model.Patient;

//import dtu.model.Measurement;

@Component
public class PulseMeasurement {
	

	

	@Scheduled(cron = "0 */1 * * * *")
	public void reportCurrentTime() {
		int patientId = 2;
		String url = "http://localhost:8080/api/v1/patients/" + patientId + "/measurements";
			
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Measurement> request = new HttpEntity<>(new Measurement());
		Measurement measurement = new Measurement();
		measurement.setGlucoseLevel(2.0);
		restTemplate.postForObject(url, request, Measurement.class,measurement);
		
		
		System.out.println("Hello world");
	}
	


}

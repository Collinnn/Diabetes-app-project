package dtu.components;

import java.sql.Timestamp;

import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import dtu.model.Measurement;
import dtu.model.MeasurementId;


@Component
public class PulseMeasurement {
	

	

	@Scheduled(cron = "0 */1 * * * *")
	public void reportCurrentTime() {
		int patientId = 1;
		String url = "http://localhost:8080/api/v1/patients/" + patientId + "/measurements";
			
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Measurement> request = new HttpEntity<>(new Measurement());

		
		//NEEDED TO MAKE A TIMESTAMP
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		System.out.println(ts);
		MeasurementId measurementId = new MeasurementId(ts,patientId);
		
		request.getBody().setMeasurementId(measurementId);
		System.out.println(request.getBody().getMeasurementId().getTimestamp());
		
		restTemplate.postForObject(url, request, Measurement.class);
		
		
		System.out.println("Hello world");
		
	}
	


}

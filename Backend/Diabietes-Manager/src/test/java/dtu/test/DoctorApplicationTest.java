package dtu.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.print.attribute.standard.Media;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dtu.Application;
import dtu.model.Doctor;
import dtu.repositories.DoctorRepository;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class )
@AutoConfigureMockMvc
public class DoctorApplicationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private DoctorRepository repository;
	

	@After
	public void resetDb() {
		repository.deleteAll();
	}
	

	@Test
	public void getaDoctor() throws Exception{
		
		createTestDoctor("john", "doe", "password");
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/doctors")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("john"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("doe"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("password"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].patients").isEmpty());
	}
	


	@Test
	public void getAllWithZeroDoctors() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/doctors")
		.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$[*].firstName").doesNotExist())
		.andExpect(MockMvcResultMatchers.jsonPath("$[*].lastName").doesNotExist())
		.andExpect(MockMvcResultMatchers.jsonPath("$[*].password").doesNotExist())
		.andExpect(MockMvcResultMatchers.jsonPath("$[*].patients").doesNotExist());
	}
	
	@Test
	public void postDoctor() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/doctors")
		.accept(MediaType.APPLICATION_JSON)
		.content((toJsonString(new Doctor("john","doe","password"))))
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("john"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("doe"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.password").value("password"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.patients").isEmpty());
	}

	@Test
	public void deleteDoctor() throws Exception{
		createTestDoctor("john", "doe", "password");
		mvc.perform(MockMvcRequestBuilders.delete("/api/v1/doctor/{doctorid}",1))
		.andExpect(status().isNotFound())
		.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").doesNotExist())
		.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").doesNotExist())
		.andExpect(MockMvcResultMatchers.jsonPath("$.password").doesNotExist())
		.andExpect(MockMvcResultMatchers.jsonPath("$.patients").doesNotExist());
	}


	public static String toJsonString(final Object obj){
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}


	private void createTestDoctor(String firstName,String lastName,String password) {
		Doctor doctor = new Doctor(firstName,lastName,password);
        repository.saveAndFlush(doctor);
    }
}

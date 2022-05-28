package dtu.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
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
	public void getAllDoctors() throws Exception{		
		createTestDoctor("john", "doe", "password");
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/doctors")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("john"));
	}
	




	private void createTestDoctor(String firstName,String lastName,String password) {
		Doctor doctor = new Doctor(firstName,lastName,password);
        repository.saveAndFlush(doctor);
    }
}

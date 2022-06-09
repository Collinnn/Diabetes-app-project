package dtu.test;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.print.attribute.standard.Media;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.invocation.mockref.MockReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dtu.Application;
import dtu.model.Doctor;
import dtu.model.Patient;
import dtu.repositories.DoctorRepository;
import dtu.repositories.PatientRepository;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class )
@AutoConfigureMockMvc
public class PatientApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PatientRepository repository;

    @AfterEach
	public void resetDb() {
		try{
			mvc.perform(MockMvcRequestBuilders.delete("/api/v1/patients/{patientid}", 1));
		} catch  (Exception e){
			throw new RuntimeException(e);
		}

	}
    @Test
	public void getaPatient() throws Exception{
		
		createTestPatient("john", "doe", "password","22.12.21");
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/patients")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("john"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("doe"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("password"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].dateOfBirth").value("22.12.21"));
	}
    @Test
	public void getAllWithZeroPatients() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/patients")
		.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").doesNotExist())
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").doesNotExist())
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].password").doesNotExist())
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].dateOfBirth").doesNotExist())
        .andExpect(MockMvcResultMatchers.jsonPath("[1].doctor").doesNotExist());
	}

    //This test is not allowed.
	@Test 
	public void postPatients() throws Exception {
		Patient patient = new Patient();
		patient.setId(1);
		patient.setFirstName("johnny");
		patient.setLastName("deer");
		patient.setPassword("passwords");
		patient.setDateOfBirth("22.12.2019");
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/patients",2)
		.accept(MediaType.APPLICATION_JSON)
		.content((toJsonString(patient)))
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("johnny"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("deer"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.password").value("passwords"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth").value("22.12.2019"));
	
	}




    private void createTestPatient(String firstName,String lastName,String password,String dateOfBirth) {
		Patient patient = new Patient();
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setPassword(password);
		patient.setDateOfBirth(dateOfBirth);
        repository.saveAndFlush(patient);
    }
    public static String toJsonString(final Object obj){
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}


}
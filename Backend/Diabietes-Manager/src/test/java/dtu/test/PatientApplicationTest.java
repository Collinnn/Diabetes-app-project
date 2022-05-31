package dtu.test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import dtu.Application;



@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class )
@AutoConfigureMockMvc
public class PatientApplicationTest {



}
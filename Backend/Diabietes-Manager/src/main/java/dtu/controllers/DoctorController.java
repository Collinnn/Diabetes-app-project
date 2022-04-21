package dtu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dtu.repositories.DoctorRepository;

@RestController @CrossOrigin @RequestMapping("/api/v1")
public class DoctorController {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	
}

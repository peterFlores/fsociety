package com.redsocial.web.app.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redsocial.web.app.Models.Analitics;
import com.redsocial.web.app.Services.IAnaliticsService;

@RestController
@CrossOrigin(origins = "*")
public class AnaliticsController {
	
	
	@Autowired
	private IAnaliticsService service;
	
	@GetMapping("/MainPageRegistred")
	public List<Analitics> mainPageVisits() {
		return service.mainPageVisits();
	}
	
	@GetMapping("/AnaliticsTop5Post")
	public List<Analitics> listAnalitics() {
		return service.top5Post();
	}
	
	
	@GetMapping("/GenderRegistred")
	public List<Analitics> genderRegistred() {
		return service.genderRegistred();
	}

	
	@GetMapping("/Top5Week")
	public List<Analitics> top5Week() {
		return service.top5week();
	}
}

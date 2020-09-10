package com.redsocial.web.app.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redsocial.web.app.Models.Estudiante;
import com.redsocial.web.app.Services.IEstudianteService;

@RestController
@CrossOrigin(origins = "*")
public class EstudianteController {
	
	@Autowired
	private IEstudianteService service;
	
	@GetMapping("/listEstudiante")
	public List<Estudiante> listEstudiantes(){
		System.out.println(service.listEstudiantes());
		return service.listEstudiantes();
	}
	
	

}

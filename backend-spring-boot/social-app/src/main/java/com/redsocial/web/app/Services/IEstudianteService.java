package com.redsocial.web.app.Services;

import java.util.List;

import com.redsocial.web.app.Models.Estudiante;

public interface IEstudianteService {

	public void createEstudiante(Estudiante estudiante);
	public void updateEstudiante(Estudiante estudiante);
	public void deleteEstudiante(Integer id);
	
	public List<Estudiante> listEstudiantes();
	
}

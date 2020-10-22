package com.redsocial.web.app.Services;

import java.util.List;

import com.redsocial.web.app.Models.Analitics;

public interface IAnaliticsService {
	//Visitas Pagina Principal y Registrados.
	public List<Analitics> mainPageVisits();
	public List<Analitics> registred();

	
	//Quien ha hecho mas publicaciones top 5
	public List<Analitics> top5Post();
	
	//Numero de hombres y mujeres registrados, algun otro si surge.
	public List<Analitics> genderRegistred();
	
	//Top5 masvisitados de la semana
	public List<Analitics> top5week();

	
}

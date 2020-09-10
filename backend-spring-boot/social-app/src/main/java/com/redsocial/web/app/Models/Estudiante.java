package com.redsocial.web.app.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"idUser","nombre","carnet"})
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Estudiante {

	private Long idUser;
	private String nombre;
	private String carnet;
	
	
	
	public Estudiante() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Estudiante(Long idUser, String nombre, String carnet) {
		super();
		this.idUser = idUser;
		this.nombre = nombre;
		this.carnet = carnet;
	}



	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCarnet() {
		return carnet;
	}
	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	
}

package com.redsocial.web.app.Models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Visit {

	private User idUser;
	@JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
	private Date vistDate;
	private Long idVisitor;
	
	
	public Visit() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Visit(User idUser, Date vistDate, Long idVisitor) {
		super();
		this.idUser = idUser;
		this.vistDate = vistDate;
		this.idVisitor = idVisitor;
	}


	public User getIdUser() {
		return idUser;
	}
	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}
	public Date getVistDate() {
		return vistDate;
	}
	public void setVistDate(Date vistDate) {
		this.vistDate = vistDate;
	}
	public Long getIdVisitor() {
		return idVisitor;
	}
	public void setIdVisitor(Long idVisitor) {
		this.idVisitor = idVisitor;
	}

}

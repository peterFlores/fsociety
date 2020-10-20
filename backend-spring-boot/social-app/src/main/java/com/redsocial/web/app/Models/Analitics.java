package com.redsocial.web.app.Models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder({ "titleAnalitic", "idUser", "userNickname","numberPosts","genderAnalitic",
"numberGender","dateAnalitics","visitsDay","registredDay"})


public class Analitics {
	private String titleAnalitic;
	
	//TOP 5 POSTS CREATED
	@JsonInclude(Include.NON_DEFAULT)
	private long idUser;
	@JsonInclude(Include.NON_NULL)
	private String userNickname;
	@JsonInclude(Include.NON_NULL)
	private long numberPosts;
	
	//GENDERS REGISTRED	
	@JsonInclude(Include.NON_NULL)
	private String genderAnalitic;
	@JsonInclude(Include.NON_NULL)
	private long numberGender;

	//MAIN PAGE VISITS REGISTRED	
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern =  "MM/dd/yyyy")
	private Date dateAnalitics;
	@JsonInclude(Include.NON_NULL)
	private long visitsDay;
	@JsonInclude(Include.NON_NULL)
	private long registredDay;
	
	//TOP 5 VISITADOS EN LA SEMANA
	@JsonInclude(Include.NON_NULL)
	private String userName;
	@JsonInclude(Include.NON_NULL)
	private long totalVisits;

	
	
	
	public Analitics() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getTitleAnalitic() {
		return titleAnalitic;
	}
	public void setTitleAnalitic(String titleAnalitic) {
		this.titleAnalitic = titleAnalitic;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	
	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public long getNumberPosts() {
		return numberPosts;
	}
	public void setNumberPosts(long numberPosts) {
		this.numberPosts = numberPosts;
	}
	public String getGenderAnalitic() {
		return genderAnalitic;
	}
	public void setGenderAnalitic(String genderAnalitic) {
		this.genderAnalitic = genderAnalitic;
	}
	public long getNumberGender() {
		return numberGender;
	}
	public void setNumberGender(long numberGender) {
		this.numberGender = numberGender;
	}
	public Date getDateAnalitics() {
		return dateAnalitics;
	}
	public void setDateAnalitics(Date dateAnalitics) {
		this.dateAnalitics = dateAnalitics;
	}
	public long getVisitsDay() {
		return visitsDay;
	}
	public void setVisitsDay(long visitsDay) {
		this.visitsDay = visitsDay;
	}
	public long getRegistredDay() {
		return registredDay;
	}
	public void setRegistredDay(long registredDay) {
		this.registredDay = registredDay;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getTotalVisits() {
		return totalVisits;
	}

	public void setTotalVisits(long totalVisits) {
		this.totalVisits = totalVisits;
	}
	
	

	

	

}

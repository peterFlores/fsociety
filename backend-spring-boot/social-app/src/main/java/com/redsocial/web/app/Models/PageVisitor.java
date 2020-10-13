package com.redsocial.web.app.Models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "ipVisitor", "visitDate", "pageDescription"})

public class PageVisitor {

	@JsonFormat(pattern = "YYYY-MM-DD")
	private Date visitDate;
	
	private String ipVisitor;

	private String pageDescription;
	
	

	public PageVisitor(Date visitDate, String ipVisitor, String pageDescription) {
		super();
		this.visitDate = visitDate;
		this.ipVisitor = ipVisitor;
		this.pageDescription = pageDescription;
	}

	public PageVisitor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getIpVisitor() {
		return ipVisitor;
	}

	public void setIpVisitor(String ipVisitor) {
		this.ipVisitor = ipVisitor;
	}

	public String getPageDescription() {
		return pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}


	
}

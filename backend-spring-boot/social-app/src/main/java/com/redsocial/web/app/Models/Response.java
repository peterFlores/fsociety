package com.redsocial.web.app.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"code","message","data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	
	

	private String Code;
	private String Message;
	private List <User> Users;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object Data;
	
	
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}

	public Object getData() {
		return Data;
	}

	public void setData(Object data) {
		Data = data;
	}
	
	

	public List<User> getUsers() {
		return Users;
	}

	public void setUsers(List<User> users) {
		Users = users;
	}
	

	public Response(String code, String message, Object data) {
		super();
		this.Code = code;
		this.Message = message;
		this.Data = data;
	}

	public Response(String code, String message) {
		// TODO Auto-generated constructor stub
		this.Code = code;
		this.Message = message;
	}

	public Response(String code, String message, List<User> users) {
		super();
		Code = code;
		Message = message;
		Users = users;
	}

	
	
	
}

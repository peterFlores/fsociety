package com.pflores.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "code", "message", "data" })

public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3088728961819665677L;

	private String Code;
	private String Message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<User> Data;

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

	

	

	public List<User> getData() {
		return Data;
	}

	public void setData(List<User> data) {
		Data = data;
	}

	public Response(String code, String message) {
		// TODO Auto-generated constructor stub
		this.Code = code;
		this.Message = message;
	}

}

package com.pflores.models;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"idUser","userName","userNickname","userMail","userPassword","userImage",
	"userBirthDate","userGender","userRole","userCreatedAt","userStatus"})

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9050053303622055999L;

	private Long idUser;

	private String userName;
	
	private String userNickname;

	
	private String userMail;
	
	private String userPassword;
	
	private String userImage;

	@JsonFormat(pattern="DD-MM-YYYY")
	private Date userBirthDate;

	private String userGender;

	private String userRole;

	@JsonFormat(pattern="DD-MM-YYYY")
	private Date userCreatedAt;

	private String userStatus;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public User(Long idUser, String userName, String userNickname, String userMail, String userPassword,
			String userImage, Date userBirthDate, String userGender, String userRole, Date userCreatedAt,
			String userStatus) {
		super();
		this.idUser = idUser;
		this.userName = userName;
		this.userNickname = userNickname;
		this.userMail = userMail;
		this.userPassword = userPassword;
		this.userImage = userImage;
		this.userBirthDate = userBirthDate;
		this.userGender = userGender;
		this.userRole = userRole;
		this.userCreatedAt = userCreatedAt;
		this.userStatus = userStatus;
	}



	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public Date getUserBirthDate() {
		return userBirthDate;
	}

	public void setUserBirthDate(Date userBirthDate) {
		this.userBirthDate = userBirthDate;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Date getUserCreatedAt() {
		return userCreatedAt;
	}

	public void setUserCreatedAt(Date userCreatedAt) {
		this.userCreatedAt = userCreatedAt;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	

}
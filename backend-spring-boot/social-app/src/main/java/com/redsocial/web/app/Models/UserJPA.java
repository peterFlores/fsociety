package com.redsocial.web.app.Models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idUser", "userName", "userNickname", "userMail", "userPassword", "userImage", "userBirthDate",
		"userGender", "userRole", "userCreatedAt", "userStatus" })

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "USERS", schema = "FSOCIETY")
public class UserJPA {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "USER_NICKNAME")
	private String userNickname;

	@Column(name = "USER_MAIL")
	private String userMail;

	@Column(name = "USER_PASSWORD")
	private String userPassword;

	@Column(name = "USER_IMAGE_PATH")
	private String userImage;

	@Column(name = "USER_BIRTHDATE")
	@JsonFormat(pattern = "YYYY-MM-DD")
	private Date userBirthDate;

	@Column(name = "USER_GENDER")
	private String userGender;

	@Column(name = "USER_ROLE")
	private String userRole;

	@Column(name = "USER_CREATED_AT")
	@JsonFormat(pattern =  "MM/dd/yyyy hh:mm:ss a")
	private Date userCreatedAt;

	@Column(name = "USER_STATUS")
	private String userStatus;
	
    @JsonManagedReference
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("createdAt DESC")
	private Set<Post> posts;
	
	public UserJPA() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserJPA(Long idUser, String userName, String userNickname, String userMail, String userPassword,
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

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

}

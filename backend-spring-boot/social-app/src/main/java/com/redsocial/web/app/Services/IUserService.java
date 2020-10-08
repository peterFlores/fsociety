package com.redsocial.web.app.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.redsocial.web.app.Models.User;
import com.redsocial.web.app.Models.UserJPA;

public interface IUserService {
	
	// CURD Users SQL use of procedures.

	public void createUser(User user) throws Exception;

	public void updateUser(User user) throws Exception;

	public void deleteUser(Integer idUser);

	public List<User> listUsers();

	public List<User> findByUserId(Integer idUser);
	
	public UserJPA findByUserId(Long id);

	// Search with LIKE on SQL.
	
	public List<User> findBy(String searchMethod, String searchData) throws Exception;
	
	
	//Dont delete, implemented on oauth-service.
		public List<User> findByUserMail(String email);
	
	// Profile picture upload for user id.
	
	public void updatePicture(MultipartFile picture, Long idUser) throws IllegalStateException, IOException;
	
	//List top 5
	
	public List<User> listTop5();


}

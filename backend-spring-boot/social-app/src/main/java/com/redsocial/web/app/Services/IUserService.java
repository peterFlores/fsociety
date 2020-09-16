package com.redsocial.web.app.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.redsocial.web.app.Models.User;

public interface IUserService {

	public void createUser(User user) throws Exception;

	public void updateUser(User user) throws Exception;

	public void deleteUser(Integer idUser);

	public List<User> listUsers();

	public List<User> findByUserId(Integer idUser);
	
	public User findByUserId(Long id);

	public List<User> findByUserMail(String email);

	public List<User> findByUserNickname(String nickname);
	
	public List<User> findByUserName(String userName);
	
	public void updatePicture(MultipartFile picture, Long idUser) throws IllegalStateException, IOException;
	

}

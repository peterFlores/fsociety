package com.redsocial.web.app.Services;

import java.util.List;

import com.redsocial.web.app.Models.User;

public interface IUserService {

	public void createUser(User user) throws Exception;

	public void updateUser(User user) throws Exception;

	public void deleteUser(Integer idUser);

	public List<User> listUsers();

	public List<User> findByUserId(Integer idUser);

	public List<User> findByUserMail(String email);

	public List<User> findByUserNickname(String nickname);
	
	public List<User> findByUserName(String userName);

}

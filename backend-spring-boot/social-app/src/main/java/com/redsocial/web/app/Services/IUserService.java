package com.redsocial.web.app.Services;


import java.util.List;

import com.redsocial.web.app.Models.User;

public interface IUserService {
	
	public void createUser (User user) throws Exception;
	public void updateUser (User user);
	public void deleteUser (Integer idUser);
	
	public List<User> listUsers();
	
	public List<User> findById(Integer idUser);
	
	public List<User> findByUserMail(String email);

}

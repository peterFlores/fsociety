package com.redsocial.web.app.DAO;

import org.springframework.data.repository.CrudRepository;

import com.redsocial.web.app.Models.User;

public interface IUserDAO extends CrudRepository<User, Long>{

}

package com.redsocial.web.app.DAO;

import org.springframework.data.repository.CrudRepository;

import com.redsocial.web.app.Models.UserJPA;

public interface IUserDAO extends CrudRepository<UserJPA, Long>{

}

package com.redsocial.web.app.DAO;

import org.springframework.data.repository.CrudRepository;

import com.redsocial.web.app.Models.Post;

public interface IPostDAO extends CrudRepository<Post, Long> {

}

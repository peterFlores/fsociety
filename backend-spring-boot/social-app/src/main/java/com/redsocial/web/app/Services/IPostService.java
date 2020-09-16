package com.redsocial.web.app.Services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.redsocial.web.app.Models.Post;

public interface IPostService {

	public List<Post> findAll();
}

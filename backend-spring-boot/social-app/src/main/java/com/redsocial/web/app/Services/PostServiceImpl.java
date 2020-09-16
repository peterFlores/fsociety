package com.redsocial.web.app.Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redsocial.web.app.DAO.IPostDAO;
import com.redsocial.web.app.Models.Post;

@Service
public class PostServiceImpl implements IPostService {
	
	@Autowired
	private IPostDAO postDao;

	@Override
	@Transactional(readOnly = true)
	public List<Post> findAll() {
		return (List<Post>) postDao.findAll();
	}

}

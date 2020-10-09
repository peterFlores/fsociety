package com.redsocial.web.app.Services;

import com.redsocial.web.app.Models.PostJDBC;

public interface PostService {
	
	public void createPost(PostJDBC post);
	
	public void deletePost(long idPost);
}

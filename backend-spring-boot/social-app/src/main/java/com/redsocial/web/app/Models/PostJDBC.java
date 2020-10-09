package com.redsocial.web.app.Models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"userId","postContent","imagePath"})

public class PostJDBC {
	
	private long postId;
	private long PostCommentId;
	private long userId;
	private String postContent;
	private String imagePath;
	
	@JsonFormat(pattern =  "MM/dd/yyyy hh:mm:ss a")
	private Date createdAt;
	
	public PostJDBC() {
		
	}
	
	
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public long getPostCommentId() {
		return PostCommentId;
	}
	public void setPostCommentId(long postCommentId) {
		PostCommentId = postCommentId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
}
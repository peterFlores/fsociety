package com.redsocial.web.app.Models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "POST", schema = "FSOCIETY")
public class Post implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "POST_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "POST_CONTENT")
	private String content;
	
	@Column(name = "POST_IMAGE_PATH")
	private String imagePath;
	
	@Column(name = "POST_CREATED_AT")
	private Date createdAt;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;
	
	@JsonBackReference
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="POST_COMMENT")
	private Post parentPost;
	
	@JsonManagedReference
    @OneToMany(mappedBy="parentPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    private Set<Post> linkedPost = new HashSet<Post>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Post getParentPost() {
		return parentPost;
	}


	public void setParentPost(Post parentPost) {
		this.parentPost = parentPost;
	}


	public Set<Post> getLinkedPosts() {
		return linkedPost;
	}


	public void setLinkedPosts(Set<Post> linkedPost) {
		this.linkedPost = linkedPost;
	}
    
    
}

package com.redsocial.web.app.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redsocial.web.app.Models.Post;
import com.redsocial.web.app.Models.Response;
import com.redsocial.web.app.Services.IPostService;

@RestController
@CrossOrigin("*")
@RequestMapping("post")
public class PostController {

	@Autowired
	private IPostService service;
	
	@GetMapping("/posts")
	public ResponseEntity<Response> getPosts() {
		
		try {
			List<Post> posts = service.findAll();
			return ResponseEntity.ok().body(new Response("00", "SUCCESS", posts));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("-1", "ERROR"));
		}
	}
}

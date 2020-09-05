package com.redsocial.web.app.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.redsocial.web.app.Models.Response;
import com.redsocial.web.app.Models.User;
import com.redsocial.web.app.Services.IUserService;

@RestController
public class UserController {
	

	@Autowired
	private IUserService service;
	
	@PostMapping(value = "/createUser" ,consumes = { MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_FORM_URLENCODED_VALUE  } , produces = "application/json")
	public Response AddUser(@RequestBody User user) {
		
		service.createUser(user);
		Response response =  new Response("1","SUCCESS",user);

		return response;
		
	}
	
	@PutMapping(value = "/updateUser/{id}" ,consumes = { MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_FORM_URLENCODED_VALUE } , produces = "application/json") 
	public Response updateUser(@PathVariable Integer id,@RequestBody User user) {
		service.updateUser(user);
		
		Response response =  new Response("1","SUCCESS",user);

		return response;
	}
	
	
	@DeleteMapping("/removeUser/{id}")
	public Response RemoveUSer(@PathVariable Integer id) {
		service.deleteUser(id);
		
		Response response =  new Response("00","SUCCESS");

		return response;
	}
	
}

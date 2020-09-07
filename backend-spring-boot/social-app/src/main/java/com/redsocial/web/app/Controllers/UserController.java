package com.redsocial.web.app.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/listUser")
	public List<User> listUsers() {
		return service.listUsers();
	}

	@PostMapping(value = "/createUser", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = "application/json")
	public Response AddUser(@RequestBody User user) throws Exception {

		Response response = null;
		try {
			service.createUser(user);

			response = new Response("1", "SUCCESS", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response = new Response("2", e.getMessage(), user);

			e.printStackTrace();
		}

		return response;

	}

	@PutMapping(value = "/updateUser/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = "application/json")
	public Response updateUser(@PathVariable Integer id, @RequestBody User user) throws Exception {
		service.updateUser(user);

		Response response = null;
		try {
			service.createUser(user);

			response = new Response("1", "SUCCESS", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response = new Response("2", e.getMessage(), user);

			e.printStackTrace();
		}

		return response;
	}

	@DeleteMapping("/removeUser/{id}")
	public Response RemoveUser(@PathVariable Integer id) {
		service.deleteUser(id);

		Response response = new Response("00", "SUCCESS");

		return response;
	}

	@GetMapping("/search/userId/{idUser}")
	public Response FindUserId(@PathVariable("idUser") Integer idUser) {
		List<User> userFound = service.findByUserId(idUser);

		Response response = new Response("00", "SUCCESS", userFound);

		return response;
	}

	@GetMapping("/search/userEmail/{email}")
	public Response FindUserEmail(@PathVariable("email") String email) {
		List<User> userFound = service.findByUserMail(email);

		Response response = new Response("00", "SUCCESS", userFound);

		return response;
	}

	@GetMapping("/search/userNickname/{nickname}")
	public Response FindUserNickname(@PathVariable("nickname") String nickname) {
		List<User> userFound = service.findByUserNickname(nickname);

		Response response = new Response("00", "SUCCESS", userFound);

		return response;
	}
}

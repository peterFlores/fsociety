package com.redsocial.web.app.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.redsocial.web.app.Models.Response;
import com.redsocial.web.app.Models.User;
import com.redsocial.web.app.Models.UserJPA;
import com.redsocial.web.app.Services.IUserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private IUserService service;

	@GetMapping("/search_user/{id}")
	public ResponseEntity<Response> getUserById(@PathVariable("id") Long id) {
		try {
			UserJPA user = service.findByUserId(id);
			return ResponseEntity.ok().body(new Response("1", "SUCCESS", user));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("-1", "ERROR"));
		}
	}

	@GetMapping("/listUser")
	public List<User> listUsers() {
		return service.listUsers();
	}

	@PostMapping(value = "/createUser", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = "application/json")
	public @ResponseBody Response AddUser(@RequestBody  User user) throws Exception {

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
	
	
	@PostMapping(value = "/createUser", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = "application/json")
	public @ResponseBody Response AddUser2(User user) throws Exception {

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

	@ResponseBody @PutMapping(value = "/updateUser/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = "application/json")
	public Response updateUser(@PathVariable Long id, @RequestBody User user) throws Exception {

		Response response = null;

		if (user.getIdUser().equals(id)) {
			
			try {
				service.updateUser(user);

				response = new Response("1", "SUCCESS", user);

			} catch (Exception e) {
				response = new Response("2", e.getMessage(), user);

				e.printStackTrace();
			}

		} 
		else {
		response = new Response("2", "INCORRECT ID", user);
		}
		
		return response;
	}
	
	
	@ResponseBody @PutMapping(value = "/updateUser/{id}", consumes = { 
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = "application/json")
	public Response updateUser2(@PathVariable Long id, User user) throws Exception {

		Response response = null;

		if (user.getIdUser().equals(id)) {
			
			try {
				service.updateUser(user);

				response = new Response("1", "SUCCESS", user);

			} catch (Exception e) {
				response = new Response("2", e.getMessage(), user);

				e.printStackTrace();
			}

		} 
		else {
		response = new Response("2", "INCORRECT ID", user);
		}
		
		return response;
	}

	@DeleteMapping("/removeUser/{id}")
	public Response RemoveUser(@PathVariable Integer id) {
		service.deleteUser(id);

		Response response = new Response("1", "SUCCESS");

		return response;
	}

	@GetMapping("/search/{searchMethod}/{searchData}")
	public Response FindUserBy(@PathVariable("searchMethod") String searchMethod,
			@PathVariable("searchData") String searchData) throws Exception {

		List<User> userFound = null;
		Response response = null;
		try {
			userFound = service.findBy(searchMethod, searchData);

			response = new Response("1", "SUCCESS", userFound);
		} catch (Exception e) {
			response = new Response("2", e.getMessage(), userFound);

			e.printStackTrace();
		}

		return response;
	}

	// Dont delete, implemented on oauth-service

	@GetMapping("/search/email/{email}")
	public User FindUserEmail(@PathVariable("email") String email) {
		List<User> userFound = service.findByUserMail(email);
		System.out.println(userFound.get(0).getUserMail());
		Response response = new Response("1", "SUCCESS", userFound);

		return service.findByUserMail(email).get(0);
	}
	
	

	@PostMapping(value = "/upload/picture/{idUser}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

	public Response UpdatePicture(@PathVariable("idUser") Long idUser, @RequestParam("picture") MultipartFile picture,
			RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {

		service.updatePicture(picture, idUser);

		Response response = new Response("1", "SUCCESS");

		return (response);

	}

	@GetMapping("/listTop5")
	public List<User> listTop5() {
		return service.listTop5();
	}

	
}

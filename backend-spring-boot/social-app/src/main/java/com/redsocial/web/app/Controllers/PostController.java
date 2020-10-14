package com.redsocial.web.app.Controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redsocial.web.app.Models.Post;
import com.redsocial.web.app.Models.PostJDBC;
import com.redsocial.web.app.Models.Response;
import com.redsocial.web.app.Services.IPostService;
import com.redsocial.web.app.Services.PostService;

@RestController
@CrossOrigin("*")
@RequestMapping("post")
public class PostController {

	@Autowired
	private IPostService service;
	
	@Autowired
	private PostService jdbcPostService;

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

		
		@PostMapping(value = "/Createpost", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = "application/json")
		public Response createPost(PostJDBC post) throws Exception {

			Response response = null;
			String imageName = java.util.UUID.randomUUID().toString();
			String imagenB64 = post.getImagePath().toString();
			String imagePath = "/var/www/html/Images/Post/" + imageName + ".jpg";
			String route = "http://3.22.230.92/Images/Post/"+ imageName + ".jpg";

			
			this.decoder(imagenB64, imagePath);
			post.setImagePath(route);
			
			try {
				jdbcPostService.createPost(post);
				
				
				response = new Response("1", "SUCCESS", post);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				response = new Response("2", e.getMessage(), post);

				e.printStackTrace();
			}

			return response;

		}
	
	@org.springframework.web.bind.annotation.DeleteMapping("/removePost/{id}")
	public Response RemoveUser(@org.springframework.web.bind.annotation.PathVariable Integer id) {
		jdbcPostService.deletePost(id);
		Response response = new Response("1", "SUCCESS");
		return response;
	}

	 public void decoder(String base64Image, String pathFile) {
		  try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
		    // Converting a Base64 String into Image byte array
		    byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
		    imageOutFile.write(imageByteArray);
		  } catch (FileNotFoundException e) {
		    System.out.println("Image not found" + e);
		  } catch (IOException ioe) {
		    System.out.println("Exception while reading the Image " + ioe);
		  }
		}
	 

}

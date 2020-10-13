package com.redsocial.web.app.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.redsocial.web.app.Models.PageVisitor;
import com.redsocial.web.app.Models.Response;
import com.redsocial.web.app.Services.IPageVisitorService;

@RestController
@CrossOrigin(origins = "*")
public class PageVisitorController {
	
	@Autowired
	private IPageVisitorService service;
	
	@PostMapping(value = "/createVisitPage", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = "application/json")
	public Response AddUser(@RequestBody PageVisitor visitor,  HttpServletRequest request) throws Exception {

		Response response = null;
		try {
			visitor.setIpVisitor(request.getRemoteAddr());
			service.createVisit(visitor);
			
			response = new Response("1", "SUCCESS", visitor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response = new Response("2", e.getMessage(), visitor);

			e.printStackTrace();
		}

		return response;

	}
	
	

	
}


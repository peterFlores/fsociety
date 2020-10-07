package com.redsocial.web.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redsocial.web.app.Models.Response;
import com.redsocial.web.app.Models.Visit;
import com.redsocial.web.app.Services.IVisitiService;

@RestController
@CrossOrigin(origins = "*")
public class VisitController {
	@Autowired
	private IVisitiService service;	
	
	@PostMapping(value = "/visit/{id}/{idVisitor}")
	public Response createVisit(@PathVariable Integer id, Integer idVisitor, Visit visit) throws Exception {

		Response response = null;
		try {
			service.createVisit(visit);

			response = new Response("1", "SUCCESS");
		} catch (Exception e) {
			response = new Response("2", "FAIL");

			e.printStackTrace();
		}

		return response;
	}
	
	
}

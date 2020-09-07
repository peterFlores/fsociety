package com.pflores.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pflores.models.Response;

@FeignClient(name = "social-app" ,url = "http://52.15.178.31:40000")
public interface IUserFeignClient {

	@GetMapping("/search/email/{email}")
	public Response findByEmail(@PathVariable("email") String email);
}

package com.pflores.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pflores.models.Response;
import com.pflores.models.User;

@FeignClient(name = "social-app" ,url = "http://3.22.230.92:40000")
public interface IUserFeignClient {

	@GetMapping("/search/email/{email}")
	public User findByEmail(@PathVariable("email") String email);
}

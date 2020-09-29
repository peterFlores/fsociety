package com.pflores.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pflores.clients.IUserFeignClient;
import com.pflores.models.Response;
import com.pflores.models.User;
import com.pflores.security.IUserService;

@Service
public class UserService implements UserDetailsService, IUserService {
	
	@Autowired
	private IUserFeignClient client;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("ere");
		// TODO Auto-generated method stub
		//Response response = client.findByEmail(username);
//		System.out.println(response.toString());
//
//		List<User> users = response.getData();
				
		User user = client.findByEmail(username);
		System.out.println(user.toString());


		if (user == null) {
			throw new UsernameNotFoundException("ERROR EMAIL NO REGISTRADO O ERRONEO");
		}
		System.out.println(username);
		System.out.println(user.getUserRole());
		GrantedAuthority gauth = new SimpleGrantedAuthority(user.getUserRole());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(gauth);
		return new org.springframework.security.core.userdetails.User(user.getUserMail(), 
				user.getUserPassword(), true, 
				true, true, true, authorities);
	}

	@Override
	public User findByEmail(String email) {
//		Response response = client.findByEmail(email);
//		List<User> users = response.getData();
		User user = client.findByEmail(email);
		return user;
	}

}

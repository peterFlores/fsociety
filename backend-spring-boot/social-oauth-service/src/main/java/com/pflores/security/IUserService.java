package com.pflores.security;

import com.pflores.models.User;

public interface IUserService {
	public User findByEmail(String email);
}

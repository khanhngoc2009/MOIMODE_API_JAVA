package com.it15306.services;

import java.util.Optional;

import com.it15306.entities.User;

public interface IUserService {
	Optional<User> findByUsername(String userName);
}

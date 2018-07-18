package com.pktech.service;

import com.pktech.entity.User;

public interface UserService {
	public User findUserById(String id);
	public void saveUser(User user);
	public void addimage(User user);
}

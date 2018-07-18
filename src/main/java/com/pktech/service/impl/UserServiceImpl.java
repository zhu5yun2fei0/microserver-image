package com.pktech.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pktech.dao.UserDao;
import com.pktech.entity.User;
import com.pktech.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	@Override
	public User findUserById(String id) {
		return userDao.findUserById(id);
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public void addimage(User user) {
		userDao.addimage(user);
	}
	
	
}

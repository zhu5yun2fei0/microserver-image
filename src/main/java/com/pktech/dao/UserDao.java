package com.pktech.dao;

import org.apache.ibatis.annotations.Mapper;

import com.pktech.entity.User;

@Mapper
public interface UserDao {
	public User findUserById(String id);
	public void saveUser(User user);
	public void addimage(User user);
}

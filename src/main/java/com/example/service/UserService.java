package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;
import com.example.model.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public List<User> getByIdPassword(String id, String password) {
		return userMapper.getByIdPassword(id, password);

	};

	public List<User> getAll() {
		return userMapper.getAll();

	};

}
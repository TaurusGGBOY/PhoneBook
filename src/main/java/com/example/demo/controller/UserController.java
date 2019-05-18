package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Friend;
import com.example.model.User;
import com.example.service.FriendService;
import com.example.service.UserService;

@RestController
public class UserController {

	@Autowired

	private UserService userService = new UserService();
	@Autowired
	private FriendService friendService = new FriendService();

	@RequestMapping("/getfriend")
	public List<Friend> getFriend(@RequestParam("id") String id) {
		return friendService.getAll(id);

	}

	@RequestMapping("/getall")
	public List<User> getAll() {
		return userService.getAll();

	}

}
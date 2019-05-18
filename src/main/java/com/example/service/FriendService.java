package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.FriendMapper;
import com.example.model.Friend;

@Service
public class FriendService {
	@Autowired
	private FriendMapper friendMapper;

	public List<Friend> getAll(String id) {
		return friendMapper.getAll(id);

	}

}
package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.Friend;

public interface FriendMapper {
	@Select("SELECT * FROM friend1 where id=#{id}")
	List<Friend> getAll(@Param("id") String id);
}
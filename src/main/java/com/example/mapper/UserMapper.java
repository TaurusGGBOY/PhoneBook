package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.User;

public interface UserMapper {
	@Select("SELECT * FROM javaeeuser1")
	List<User> getAll();

	@Select("SELECT * FROM javaeeuser1 where id=#{id} and pwd=#{password}")
	List<User> getByIdPassword(@Param("id") String id, @Param("password") String password);

}
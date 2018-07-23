package com.example.demo.dao;

import org.apache.ibatis.annotations.Select;

import com.example.demo.pojo.User;

public interface UserDao {

	@Select("select * from user where username=#{username}")
	public User findUserByUsername(String username);
	
}

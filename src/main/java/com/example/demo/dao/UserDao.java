package com.example.demo.dao;

import org.apache.ibatis.annotations.Select;

import com.example.demo.pojo.User;

/**
 * 用户表相关的dao类
 * @author virtualspider
 *
 */
public interface UserDao {

	@Select("select * from user where username=#{username}")
	public User findUserByUsername(String username);
	
}

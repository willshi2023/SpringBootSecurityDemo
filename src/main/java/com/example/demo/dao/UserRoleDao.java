package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.demo.pojo.UserRole;

/**
 * 用户角色中间表的dao类
 * @author Administrator
 *
 */
public interface UserRoleDao {

	/**
	 * 通过用户id获取角色信息
	 * @param userId
	 * @return
	 */
	@Select("SELECT r.id,r.name FROM user_role ur\r\n" + 
			"LEFT JOIN role r ON ur.`role_id`=r.`id`\r\n" + 
			"WHERE ur.id=#{userId}")
	public List<UserRole> getRolesByUserId(Long userId);
	
}

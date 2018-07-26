package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.demo.pojo.Role;

/**
 * 角色资源中间表的dao类
 * @author virtualspider
 *
 */
public interface RoleResourceDao {
	/**
	 * 通过资源的id获取所有的角色
	 * 		先通过role_resource中间表的resource_id查出对应的role_id
	 * 		再通过role表的id查出对应的所有信息
	 * @return
	 */
	@Select("SELECT r.* \r\n" + 
			"FROM role_resource rr \r\n" + 
			"LEFT JOIN role r ON rr.role_id=r.id\r\n" + 
			"WHERE rr.resource_id=#{resourceId}")
	List<Role> findRolesByResourceUrl(Long resourceId);
}

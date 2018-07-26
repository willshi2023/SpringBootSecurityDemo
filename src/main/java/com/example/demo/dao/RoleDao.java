package com.example.demo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Select;

/**
 * 角色表的中间类
 * @author virtualspider
 *
 */
public interface RoleDao {

	/**
	 * 查出所有的角色列表
	 * @return
	 */
	@Select("select * from role")
	public  List<String> findAll();

}

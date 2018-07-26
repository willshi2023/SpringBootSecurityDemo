package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.demo.pojo.Resource;

/**
 * resource资源表对应的dao类
 * @author virtualspider
 *
 */
public interface ResourceDao {
	@Select("select * from resource")
	List<Resource> findAll();
}

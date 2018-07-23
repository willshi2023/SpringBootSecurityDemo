package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.UserRoleDao;
import com.example.demo.pojo.MyUserDetails;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserRole;

/**
 * 配置springsecurity数据库管理的类
 * @author Administrator
 *
 */
@Component
public class MyUserService implements UserDetailsService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user = userDao.findUserByUsername(username);
			// SecurityUser实现UserDetails并将SUser的Email映射为username
			if(user==null) {
				throw new UsernameNotFoundException(username);
			}
			Long userId = user.getId();
			List<UserRole> roles = userRoleDao.getRolesByUserId(userId);
			return new MyUserDetails(user,roles);
	}

}

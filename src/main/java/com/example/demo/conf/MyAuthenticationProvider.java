package com.example.demo.conf;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.MyUserDetails;
import com.example.demo.service.MyUserDetailService;

/**
 * 自定义用户名和密码验证
 * @author Administrator
 *
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private MyUserDetailService myUserDetailService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		MyUserDetails user = (MyUserDetails) myUserDetailService.loadUserByUsername(username);
		if(user==null) {
			throw new BadCredentialsException("用户名未找到");
		}
		
		//加密过程在这里体现
		if(!password.equals(user.getPassword())) {
			throw new BadCredentialsException("密码不正确");
		}
		
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}

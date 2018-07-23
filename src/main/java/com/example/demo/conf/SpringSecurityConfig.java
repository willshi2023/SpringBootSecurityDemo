package com.example.demo.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring().antMatchers("/js/**","/css/**","/images/**");//对于这些静态文件，忽略拦截
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()//访问首页不需要权限，其他页面需要权限
				.antMatchers("/").permitAll().anyRequest().authenticated().and()
			.logout()//退出不需要权限
				.permitAll().and()
			.formLogin()//支持表单登陆
				.and()
			.csrf()//关闭默认的csrf认证
				.disable();
	}
}

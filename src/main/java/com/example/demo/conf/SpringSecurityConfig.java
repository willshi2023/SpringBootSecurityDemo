package com.example.demo.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()//设置一个内存中的用户密码，早期的版本不需要设置密码登陆，springsecurity5之后需要
				.passwordEncoder(new BCryptPasswordEncoder()).withUser("admin")
				.password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");
	}
}

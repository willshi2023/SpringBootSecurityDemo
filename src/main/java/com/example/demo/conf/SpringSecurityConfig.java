package com.example.demo.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * springsecurity的自定义配置类
 * @author Administrator
 *
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	private MyUserService myUserService;
	
	@Autowired
    private MyAuthenticationProvider provider;//自定义验证
	
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
//		auth
//			.inMemoryAuthentication()//设置一个内存中的用户密码，早期的版本不需要设置密码登陆，springsecurity5之后需要
//				.passwordEncoder(passwordEncoder).withUser("admin")
//				.password(passwordEncoder.encode("123456")).roles("ADMIN")
//				.and()
//				.passwordEncoder(passwordEncoder).withUser("zhangsan")
//				.password(passwordEncoder.encode("123456")).roles("USER");
		auth.authenticationProvider(provider);
	}
	
	/**
	 * 手动在拦截器中配置注册一个单例的bean对象，避免每次都重新生成
	 * @return
	 */
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
}

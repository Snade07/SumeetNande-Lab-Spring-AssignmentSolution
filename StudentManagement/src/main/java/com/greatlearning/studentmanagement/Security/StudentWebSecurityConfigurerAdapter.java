package com.greatlearning.studentmanagement.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.studentmanagement.Service.StudentUserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
public class StudentWebSecurityConfigurerAdapter 
	extends WebSecurityConfigurerAdapter{

	// Customize Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		AuthenticationProvider authenticationProvider = customAuthenticationProvider();
		auth.authenticationProvider(authenticationProvider);
		
	}
	
	@Bean
	public UserDetailsService customUserDetailsService() {
		return new StudentUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider customAuthenticationProvider() {
		
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		
		daoProvider.setUserDetailsService(customUserDetailsService());
		daoProvider.setPasswordEncoder(bcryptPasswordEncoder());
		
		return daoProvider;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/", "/student/save", "/student/add-begin", "/student/403")
				.hasAnyAuthority("NORMAL_USER", "ADMIN_USER")

			.antMatchers("/student/update-begin", "/student/delete")
				.hasAuthority("ADMIN_USER")
			.anyRequest().authenticated().and()
			.formLogin()
				.loginProcessingUrl("/login").successForwardUrl("/students/list")
			.permitAll().and().
			logout()
				.logoutSuccessUrl("/login").permitAll().and().
			exceptionHandling()
				.accessDeniedPage("/students/403").and()
			.cors().and().csrf().disable();
	}	

}


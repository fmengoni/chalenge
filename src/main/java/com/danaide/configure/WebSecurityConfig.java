package com.danaide.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/", "/home").permitAll()
			.and().formLogin().loginPage("/login").permitAll()
			.and().logout().permitAll();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails adminUser = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN").build();
		UserDetails finalUser = User.withDefaultPasswordEncoder().username("final").password("final").roles("FINAL").build();
		return new InMemoryUserDetailsManager(adminUser, finalUser);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v1/**");
	}
}
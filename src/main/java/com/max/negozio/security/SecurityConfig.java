package com.max.negozio.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// Authentication : User e relativi ruoli
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
				.withUser("max").password("{noop}passUser").roles("USER")
				.and()
				.withUser("admin").password("{noop}passAdmin").roles("ADMIN");
	}

	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
		.and().authorizeRequests()
		.antMatchers("/")
		.permitAll()
		.antMatchers("/admin")
		.hasRole("ADMIN")
		.antMatchers("/user")
		.hasRole("USER").and().csrf().disable();
	}
}

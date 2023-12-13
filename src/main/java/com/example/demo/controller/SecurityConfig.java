//package com.example.demo.controller;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.config.EnableWebFlux;
//
//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebFlux
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/receive-data").permitAll() // Endpoint that allows unauthenticated access
//				// Add other security configurations
//				.anyRequest().authenticated().and().csrf().disable(); // Disable CSRF for simplicity (consider enabling
//																		// it in production)
//	}
//}

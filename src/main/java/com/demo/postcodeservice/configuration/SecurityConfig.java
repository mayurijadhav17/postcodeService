package com.demo.postcodeservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
@Bean
public InMemoryUserDetailsManager userDetailsService() {
	UserDetails user = User.withDefaultPasswordEncoder()
																												.username("root")
																												.password("rootpass")
																												.roles("ADMIN")
																												.build();
	return new InMemoryUserDetailsManager(user);
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
																																																					.permitAll()).csrf((csrf) -> csrf.disable());
	http.headers(headers -> headers.frameOptions().disable());
	return http.build();

}
}
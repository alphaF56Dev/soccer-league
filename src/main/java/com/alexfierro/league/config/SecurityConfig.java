/**
 * 
 */
package com.alexfierro.league.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author alpha
 *
 */
@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors().and()
			.csrf().disable()
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/users/auth").permitAll()
					.anyRequest().authenticated()
					);
		return http.build();
	}
}

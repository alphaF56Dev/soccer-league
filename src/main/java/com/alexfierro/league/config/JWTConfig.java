/**
 * 
 */
package com.alexfierro.league.config;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;

/**
 * @author alpha
 *
 */
@Configuration
public class JWTConfig {
	@Bean
	public Key jwtKey() {
        String secret = "SecretWordForMoreSecurity20252021";
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

}

/**
 * 
 */
package com.alexfierro.league.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * @author alpha
 *
 */
@Component
public class JWTUtil {
	private final Key key;
	
	public JWTUtil(Key key) {
		this.key = key;
	}
	
	public String generateToken(String username) {
		
		String token =  Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+3600000))
				.signWith(key)
				.compact();
				
		return token;
	}
}

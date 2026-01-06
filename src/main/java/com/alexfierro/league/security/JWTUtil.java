/**
 * 
 */
package com.alexfierro.league.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * @author alpha
 *
 */
public class JWTUtil {
	private static Key SecretKey = Keys.hmacShaKeyFor("BackToTheFutureSecretKey1985".getBytes(StandardCharsets.UTF_8));
	
	public static String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+3600000))
				.signWith(SecretKey,SignatureAlgorithm.HS256)
				.compact();
	}
}

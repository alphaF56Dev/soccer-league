/**
 * 
 */
package com.alexfierro.league.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author alpha
 *
 */
@Component
public class JWTFilter extends OncePerRequestFilter {
	private final Key SecretKey;
	
	public JWTFilter(Key key) {
		SecretKey = key;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	        filterChain.doFilter(request, response);
	        return;
	    }
		
		String authHeader = request.getHeader("Authorization");
		if(authHeader !=  null && authHeader.startsWith("Bearer ")) {
			try {
				String token = authHeader.substring(7);
				System.out.println("JWTFilter token: " + token);
				Claims claims = Jwts.parserBuilder()
				.setSigningKey(SecretKey)
				.build()
				.parseClaimsJws(token)
				.getBody();
				
				String username = claims .getSubject();
				
				System.out.println("**** username: " + username);
				
				User userDetails = new User(username, "", List.of(new SimpleGrantedAuthority("ROLE_USER")));

				UsernamePasswordAuthenticationToken authentication =
	                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	            SecurityContextHolder.getContext().setAuthentication(authentication);
	            
	            System.out.println(">>> Authentication en contexto: " + SecurityContextHolder.getContext().getAuthentication());


			} catch (Exception e) {
				System.out.println("********ERROR VALIDANDO TOKEN: " + e.getMessage());

				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		}
		
		filterChain.doFilter(request, response);
	}
}

/**
 * 
 */
package com.alexfierro.league.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alexfierro.league.entity.UserAccess;
import com.alexfierro.league.repository.UserAccessRepository;

/**
 * @author alpha
 *
 */
@Service
public class UserAccessService {
	@Autowired
	private UserAccessRepository userRep;
	
	public UserAccess validateAccess(String username, String pw) {		
		return userRep.findByUsernameAndPassword(username, pw)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, String.format("Not valid credentials!")));
	}
	
	public UserAccess addUserAccess(UserAccess username) {
		return userRep.save(username);
	}
	
	public List<UserAccess> listUsers(){
		return userRep.findAll();
	}
}

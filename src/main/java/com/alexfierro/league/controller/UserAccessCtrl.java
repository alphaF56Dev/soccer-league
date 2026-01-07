/**
 * 
 */
package com.alexfierro.league.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alexfierro.league.entity.UserAccess;
import com.alexfierro.league.entity.dto.AuthResponse;
import com.alexfierro.league.security.JWTUtil;
import com.alexfierro.league.service.UserAccessService;

/**
 * @author alpha
 *
 */
@RestController
@RequestMapping("/users")
public class UserAccessCtrl extends MainCtrl{
	@Autowired
	private UserAccessService userSrv;
	
	@PostMapping("/auth")
	public ResponseEntity<?> auth(@RequestBody HashMap<String, ?> params){
		String username = "";
		String pw = "";
		String tokenJWT = "";
		try {
			username = (String)params.get("username");
			pw = (String) params.get("pw");
			setCurrentUser(userSrv.validateAccess(username, pw));
			currentSession.setAttribute("username", username);
			
			tokenJWT = JWTUtil.generateToken(username);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new AuthResponse(tokenJWT), HttpStatus.OK);		
	}
	
	@PostMapping("/add-user")
	public ResponseEntity<?> addUser(@RequestBody UserAccess userAccess){
		if (!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		UserAccess nUser = null;
		try {
			nUser = userSrv.addUserAccess(userAccess);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(nUser, HttpStatus.OK);	
	}
	
	@GetMapping("/list-users")
	public ResponseEntity<?> listUsers(){
		if (!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(userSrv.listUsers(), HttpStatus.OK);
	}
	
}

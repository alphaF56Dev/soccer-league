/**
 * 
 */
package com.alexfierro.league.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alexfierro.league.entity.UserAccess;
import com.alexfierro.league.repository.UserAccessRepository;
import com.alexfierro.league.service.UserAccessService;

import jakarta.servlet.http.HttpSession;

/**
 * @author alpha
 *
 */
@Controller
public class MainCtrl {
	protected UserAccess currentUser = new UserAccess();
	
	@Autowired
	protected HttpSession currentSession;
	
	@Autowired
	protected UserAccessService userSrv;
	
	public boolean hasAccess() {
		getCurrentUser();
		boolean access = (currentUser != null && currentUser.getUsername() != null);
		return access;
	}

	public UserAccess getCurrentUser() {
		String username = (String)currentSession.getAttribute("username");
		currentUser = userSrv.findUserByUsername(username);
		return currentUser;
	}

	public void setCurrentUser(UserAccess currentUser) {		
		this.currentUser = currentUser;
	}
}

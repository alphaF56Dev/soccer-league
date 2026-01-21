/**
 * 
 */
package com.alexfierro.league.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alexfierro.league.entity.State;
import com.alexfierro.league.service.StateService;

/**
 * @author alpha
 *
 */
@Controller
@RequestMapping(value = "/state")
public class StateCtrl extends MainCtrl{
	@Autowired
	private StateService stateSrv;
	
	@GetMapping(value = "/list-states")
	public ResponseEntity<?> listStates(){		
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<List<State>>(stateSrv.getStates(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/save-state")
	public ResponseEntity<?> saveState(@RequestBody State state){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		State nState = null;
		try {
			nState = stateSrv.saveState(state);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}		
		return new ResponseEntity<State>(nState, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-state-byId/{stateId}")
	public ResponseEntity<?> getStateById(@PathVariable(value="stateId") Long stateId){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		State state = null;
		HttpStatus status = HttpStatus.OK;
		try {
			Optional<State> stateToLookfor = stateSrv.findStateById(stateId);
			if(stateToLookfor.isPresent()) {
				state = stateToLookfor.get();
			}else {
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return new ResponseEntity<>(state, status);
	}
}

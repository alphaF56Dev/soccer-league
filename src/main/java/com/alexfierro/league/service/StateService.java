/**
 * 
 */
package com.alexfierro.league.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.entity.State;
import com.alexfierro.league.repository.StateRepository;

/**
 * @author alpha
 *
 */
@Service
public class StateService {
	@Autowired
	private StateRepository statedRep;
	
	public List<State> getStates(){
		return statedRep.findAll();
	}
	
	public Optional<State> getStateById(Long stateId) {
		return statedRep.findById(stateId);
	}
	
	public State saveState(State newState) {
		State nState = (State)statedRep.save(newState);
		return nState;
	}
}

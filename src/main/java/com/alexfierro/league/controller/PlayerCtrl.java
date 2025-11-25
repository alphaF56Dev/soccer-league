/**
 * 
 */
package com.alexfierro.league.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexfierro.league.entity.Player;
import com.alexfierro.league.entity.dto.PlayerDto;
import com.alexfierro.league.service.PlayerService;

/**
 * @author alpha
 *
 */
@RestController
@RequestMapping(value="/player")
public class PlayerCtrl extends MainCtrl{
	@Autowired
	private PlayerService playerSrv;
	
	@GetMapping(value="/list-players")
	private ResponseEntity<?> listPlayers(){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(playerSrv.listPlayers(), HttpStatus.OK);
	}
	
	@GetMapping(value="list-playersbyStatus")
	private ResponseEntity<?> listPlayersByStatus(@RequestParam(name="isActive", defaultValue = "true", required = false) boolean isActive){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(playerSrv.listPlayerByStatus(isActive), HttpStatus.OK);
	}
	
	@GetMapping(value="/get-player-by-idmember/{idMember}")
	public ResponseEntity<?> getPlayerByidmember(@PathVariable(value="idMember") Long idMember){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		PlayerDto playerFound = null;
		HttpStatus status = HttpStatus.OK;
		try {
			playerFound = playerSrv.getPlayerByMemberId(idMember);
			if(playerFound == null) {
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(playerFound, status);
	}
	
	@GetMapping(value="/get-player-by-idplayer/{idPlayer}")
	public ResponseEntity<?> getPlayerByMemberId(@PathVariable(value="idPlayer") Long idPlayer){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		PlayerDto playerFound = null;
		HttpStatus status = HttpStatus.OK;
		try {
			playerFound = playerSrv.getPlayerByPlayerId(idPlayer);
			if(playerFound == null) {
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(playerFound, status);
	}
	
	@PostMapping(value="/save-player")
	public ResponseEntity<?> savePlayer(@RequestBody Player player){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		Player playerSaved = null;
		HttpStatus status = HttpStatus.OK;
		try {
			playerSaved = playerSrv.savePlayer(player);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(playerSaved, status);
	}
}

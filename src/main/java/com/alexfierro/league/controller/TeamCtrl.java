/**
 * 
 */
package com.alexfierro.league.controller;

import java.util.Optional;

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

import com.alexfierro.league.entity.Team;
import com.alexfierro.league.service.TeamService;

/**
 * @author alpha
 *
 */
@RestController
@RequestMapping(value="/team")
public class TeamCtrl extends MainCtrl{
	@Autowired
	private TeamService teamSrv;
	
	@GetMapping(value="/list-teams")
	public ResponseEntity<?> lisTeams(){
		if (!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(teamSrv.listTeams(), HttpStatus.OK);
	}
	
	@GetMapping(value="/list-teams-bystatus")
	public ResponseEntity<?> listTeamsByStatus(@RequestParam(name="isActive", defaultValue = "true", required = false) boolean isActive){
		if (!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(teamSrv.listTeamsByStatus(isActive), HttpStatus.OK);
	}
	
	@GetMapping(value="/get-team-byId/{idTeam}")
	public ResponseEntity<?> getItemById(@PathVariable(value = "idTeam") Long idteam){
		if (!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		
		Team team = null;
		HttpStatus status = HttpStatus.OK;
		try {
			Optional<Team> teamToLookfor = teamSrv.getTeamById(idteam);
			if(teamToLookfor.isEmpty()) {
				status = HttpStatus.NOT_FOUND;				
			}else {
				team = teamToLookfor.get();
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(team, status);
	}
	
	@PostMapping(value="/save-team")
	public ResponseEntity<?> saveTeam(@RequestBody Team team){
		if (!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		Team nTeam = null;
		try {
			nTeam = teamSrv.saveTeam(team);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(nTeam, HttpStatus.OK);
	}
}

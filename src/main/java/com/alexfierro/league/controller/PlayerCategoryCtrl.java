/**
 * 
 */
package com.alexfierro.league.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexfierro.league.service.PlayerCategoryService;

/**
 * @author alpha
 *
 */
@RestController
@RequestMapping(value="/player-category")
public class PlayerCategoryCtrl extends MainCtrl{
	@Autowired
	private PlayerCategoryService playeCatSrv;
	
	@GetMapping(value = "/list-byIdTeamCategory/{idTeamCategory}")
	public ResponseEntity<?> listByIdTeamCategory(@PathVariable(value = "idTeamCategory") Long idTeamCategory){
		if(!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(playeCatSrv.getPlayersByIdTeamCategory(idTeamCategory), HttpStatus.OK);
	}
	
	@GetMapping(value = "/list-byIdTeamAndIdCategory/{idTeam}/{idCategory}")
	public ResponseEntity<?> listaByIdteamAndIdCategory(
				@PathVariable(value="idTeam") Long idTeam,
				@PathVariable(value="idCategory") Long idCategory
			){
		if(!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(playeCatSrv.getPlayersByIdTeamAndIdCategory(idTeam, idCategory), HttpStatus.OK);
	}
	
	@GetMapping(value = "/list-teams-byIdPlayer/{idPlayer}")
	public ResponseEntity<?> ListTeamsByIdPlayer(@PathVariable(value="idPlayer") Long idPlayer){
		if(!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(playeCatSrv.getTeamsByIdPlayer(idPlayer), HttpStatus.OK);
	}
	
	@PostMapping(value="/addPlayerToCategory")
	public ResponseEntity<?> addPlayerToCategory(@RequestBody HashMap<String, ?> params){		
		if(!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		int code = 0;
		HttpStatus status = HttpStatus.OK;
		String msg = "";
		try {
			Long idPlayer = Long.valueOf((Integer) params.get("idPlayer"));
			Long idTeam = Long.valueOf((Integer) params.get("idTeam"));
			Long idCategoryLeague= Long.valueOf((Integer) params.get("idCategoryLeague"));
			short player_number = (Short.valueOf((String)params.get("player_number")));
			code = playeCatSrv.savePlayerCategory(idPlayer, idTeam, idCategoryLeague, player_number);
			switch (code) {
			case 0: {				
				msg = "The player has already linked to this team in the same category.";
				status = HttpStatus.CONFLICT;
				break;
			}
			case 1:{
				msg = "Category added to the player.";
				status = HttpStatus.NO_CONTENT;
				break;
			}
			case 2:{
				msg = "The player's age is not valid for the category selected.";
				status = HttpStatus.CONFLICT;
				break;
			}
			case 3:{
				msg = "The player has already linked to another team in the same category.";
				status = HttpStatus.CONFLICT;
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + code);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return new ResponseEntity<>(msg, status);
	}
	
	@DeleteMapping(value="/remove-byIdPlayeCetagory/{idPlayerCategory}")
	public ResponseEntity<?> removeByIdPlayerCategory(@PathVariable(value = "idPlayerCategory") Long idPlayerCategory){
		if(!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}		
		try {
			playeCatSrv.removeByIdPlayerCategory(idPlayerCategory);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/remove-byIdPlayerAndIdTeamAndIdCategory")
	public ResponseEntity<?> removeByIdPlayerAndIdTeamAndIdCategory(@RequestBody HashMap<String, ?> params){
		if(!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		int code = -1;
		HttpStatus status = HttpStatus.ACCEPTED;
		String msg = "";
		try {
			Long idPlayer = Long.valueOf((Integer)params.get("idPlayer"));
			Long idTeam = Long.valueOf((Integer)params.get("idTeam"));
			Long idCategory = Long.valueOf((Integer)params.get("idCategory"));
			code = playeCatSrv.removeByIdPlayerAndIdTeamAndIdCategory(idPlayer, idTeam, idCategory);
			switch (code) {
			case 0: {
				msg = "Record not found";
				status = HttpStatus.NOT_FOUND;
				break;
			}
			case 1: {
				msg = "Record deleted";
				status = HttpStatus.OK;
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + code);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(msg, status);
	}
	
}

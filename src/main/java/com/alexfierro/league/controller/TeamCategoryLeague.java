/**
 * 
 */
package com.alexfierro.league.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexfierro.league.service.TeamCategoryLeagueService;

/**
 * @author alpha
 *
 */
@RestController
@RequestMapping(value="/team-category")
public class TeamCategoryLeague extends MainCtrl{
	@Autowired
	private TeamCategoryLeagueService tCatLeagueSrv;
	
	@GetMapping(value="/get-teamsByIdCategoryLeague/{idCategoryLeague}")
	private ResponseEntity<?> getTeamsByCategoryLeague(@PathVariable(value="idCategoryLeague") Long idCategoryLeague){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(tCatLeagueSrv.listTeamsByIdCategoryLeague(idCategoryLeague), HttpStatus.OK);
	}
	
	@GetMapping(value="/get-categoryByIdTeam/{idTeam}")
	private ResponseEntity<?> getCategoryByIdTeam(@PathVariable(value="idTeam") Long idTeam){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(tCatLeagueSrv.listCategoriesByIdTeam(idTeam), HttpStatus.OK);
	}
	
	@PutMapping(value="/add-category-to-team/{idTeam}/{idCategoryLeague}")
	private ResponseEntity<?> addCategoryToTeam(@PathVariable(value="idTeam") Long idTeam, @PathVariable(value="idCategoryLeague") Long idCategoryLeague){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		int code = -1;
		String msg = "";
		HttpStatus status = HttpStatus.NO_CONTENT;
		try {
			code = tCatLeagueSrv.addCategoryToTeam(idTeam, idCategoryLeague);
			switch (code) {
			case 0: {
				msg = "The category has already exist to the team.";
				status = HttpStatus.CONFLICT;
				break;
			}
			case 1: {
				msg = "The category was added success to the team.";
				status = HttpStatus.OK;
				break;
			}
			default:
				msg = "Fail to add category.";
				status = HttpStatus.CONFLICT;
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			msg = e.getMessage();
		}
		return new ResponseEntity<>(msg, status);
	}
	
	@PutMapping(value = "/remove-category-from-team/{idTeam}/{idCategoryLeague}")
	public ResponseEntity<?> removeCategoryFromTeam(@PathVariable(value="idTeam") Long idTeam, @PathVariable(value="idCategoryLeague") Long idCategoryLeague){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		int code = -1;
		String msg = "";
		HttpStatus status = HttpStatus.NO_CONTENT;
		try {
			code = tCatLeagueSrv.removeCategoryOfTeam(idTeam, idCategoryLeague);
			switch (code) {
			case 0: {
				msg = "The team has not the category added yet.";
				status = HttpStatus.CONFLICT;
				break;
			}
			case 1: {
				msg = "The category was removed success to the team.";
				status = HttpStatus.OK;
				break;
			}
			default:
				msg = "Fail to remove category.";
				status = HttpStatus.CONFLICT;
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			msg = e.getMessage();
		}
		return new ResponseEntity<>(msg, status);
	}
}

/**
 * 
 */
package com.alexfierro.league.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexfierro.league.entity.CategoryLeague;
import com.alexfierro.league.service.CategoryLeagueService;

/**
 * @author alpha
 *
 */
@RequestMapping(value="/category-league")
@RestController
public class CategoryLeagueCtrl extends MainCtrl{
	@Autowired
	private CategoryLeagueService catLeagueSrv;
	
	@GetMapping(value = "/list-categoryLeague")
	public ResponseEntity<?> listCategoryLeague(){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(catLeagueSrv.listCategoryLeague(), HttpStatus.OK);
	}
	
	@GetMapping(value="/get-categoryLeagueById/{categoryLeagueId}")
	public ResponseEntity<?> getCategoryLeagueById(@PathVariable("categoryLeagueId") Long categoryLeagueId){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		CategoryLeague catLeague = null;
		HttpStatus status = HttpStatus.OK;
		try {
			catLeague = catLeagueSrv.findCategoryById(categoryLeagueId);
			if(catLeague == null) {
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(catLeague, status);
	}
	
	@PostMapping(value = "/save-categoryLeague")
	public ResponseEntity<?> saveCategoryLeague(@RequestBody CategoryLeague catergoryLeague){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		CategoryLeague nCatLeague = null;		
		try {
			nCatLeague = catLeagueSrv.saveCategoryLeague(catergoryLeague); 
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(nCatLeague, HttpStatus.OK);
	}
	
	@PutMapping(value="/change-status-categoryleague")
	public ResponseEntity<?> changeStatusCategoryleague(@RequestBody CategoryLeague catLeague){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		String msg = "";
		HttpStatus status = HttpStatus.OK;
		int code = -1;
		try {
			if(catLeague.getIsActive()) {
				code = catLeagueSrv.disableCategoryLeague(catLeague.getIdCategoryLeague());
			}else {
				code = catLeagueSrv.enableCategoryLeague(catLeague.getIdCategoryLeague());
			}
			switch (code) {
			case 0: {
				status = HttpStatus.NOT_FOUND;
				msg = "Category league to change was not found.";
				break;
			}
			case 1: {
				status = HttpStatus.OK;
				msg = "Category league was updated.";
				break;
			}
			default:
				status = HttpStatus.CONFLICT;
				msg = "Fail to update Category League";
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			msg = e.getMessage();
		}
		return new ResponseEntity<>(msg, status);
	}
	
	@GetMapping(value="/get-categoriesByAge/{currenAge}")
	public ResponseEntity<?> getCategoryByAge(@PathVariable(value="currenAge") short currentAge){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(catLeagueSrv.getCategoriesLeagueByAge(currentAge), HttpStatus.OK);
	}
}

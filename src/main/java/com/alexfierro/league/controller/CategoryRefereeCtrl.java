/**
 * 
 */
package com.alexfierro.league.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexfierro.league.service.CategoryRefereeService;


/**
 * @author alpha
 *
 */
@RestController
@RequestMapping(value="/category-referee")
public class CategoryRefereeCtrl extends MainCtrl{
	@Autowired
	private CategoryRefereeService catRefereeSrv;
	
	@GetMapping(value = "/get-refereesByCategoryId/{idCategory}")
	public ResponseEntity<?> getRefereesByCategory(@PathVariable("idCategory") Long idCategory){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}		
		return new ResponseEntity<>(catRefereeSrv.listRefereesPerCategory(idCategory), HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-categoryByRefereesId/{idReferee}")
	public ResponseEntity<?> getCategoryByRefereesId(@PathVariable("idReferee") Long idReferee){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(catRefereeSrv.listCategoryPerReferees(idReferee), HttpStatus.OK);
	}
	
	@PostMapping(value="/add-category-to-referee/{idCategoryLeague}/{idReferee}")
	public ResponseEntity<?> addCategoryToReferee(@PathVariable("idCategoryLeague") Long idCategoryLeague, @PathVariable("idReferee") Long idReferee){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		String msg = "";
		HttpStatus status = HttpStatus.OK;
		int code = 0;
		try {
			code = catRefereeSrv.addCategoryToReferee(idCategoryLeague, idReferee);
			switch (code) {
			case 0: {
				msg = "Category already exist for the referee.";
				status = HttpStatus.BAD_REQUEST;
				break;
			}
			case 1: {
				msg = "Category added to referee.";
				status = HttpStatus.OK;
				break;
			}
			default:
				status = HttpStatus.CONFLICT;
				msg = "Error trying to add category to the referee.";
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return new ResponseEntity<>(msg, status);
	}
	
	@DeleteMapping(value="/remove-category-of-referee/{idCategoryLeague}/{idReferee}")
	public ResponseEntity<?> removeCategoryOfReferee(@PathVariable("idCategoryLeague") Long idCategoryLeague, @PathVariable("idReferee") Long idReferee){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		String msg = "";
		HttpStatus status = HttpStatus.OK;
		int code = 0;
		try {
			code = catRefereeSrv.removeCategoryOfReferee(idCategoryLeague, idReferee);
			switch (code) {
			case 0: {
				msg = "Referee does not have this category added yet.";
				status = HttpStatus.BAD_REQUEST;
				break;
			}
			case 1: {
				msg = "Category was removed of the referee.";
				status = HttpStatus.OK;
				break;
			}
			default:
				status = HttpStatus.CONFLICT;
				msg = "Error trying to add category to the referee.";
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return new ResponseEntity<>(msg, status);
	}
}

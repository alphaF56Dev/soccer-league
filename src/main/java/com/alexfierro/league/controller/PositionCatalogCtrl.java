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
import org.springframework.web.bind.annotation.RestController;

import com.alexfierro.league.entity.PositionCatalog;
import com.alexfierro.league.service.PositionCatalogService;

/**
 * @author alpha
 *
 */
@RestController
@RequestMapping(value = "/position-catalog")
public class PositionCatalogCtrl extends MainCtrl {
	@Autowired
	private PositionCatalogService posCatalogSrv;
	
	@GetMapping(value = "/list-positionscatalog")
	public ResponseEntity<?> listPositionscatalog(){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(posCatalogSrv.listPositions(), HttpStatus.OK);
	}
	
	@GetMapping(value="/get-positioncatalog-byId/{posCatalogId}")
	public ResponseEntity<?> getPositionCatalogById(@PathVariable(value = "posCatalogId") Long posCatalogId){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		HttpStatus status = HttpStatus.OK;
		PositionCatalog posCatalog = null;
		try {
			posCatalog = posCatalogSrv.getPositionById(posCatalogId);
			if (posCatalog == null) {
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(posCatalog, status);
	}
	
	@PostMapping(value = "/save-positioncatalog")
	public ResponseEntity<?> savePositioncatalog(@RequestBody PositionCatalog posCatalog){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		PositionCatalog nPosCatalog = null;
		try {
			nPosCatalog = posCatalogSrv.savePositionCatalog(posCatalog);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return new ResponseEntity<>(nPosCatalog, HttpStatus.OK);
	}
}

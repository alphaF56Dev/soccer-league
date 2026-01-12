/**
 * 
 */
package com.alexfierro.league.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alexfierro.league.entity.Municipality;
import com.alexfierro.league.service.MunicipalityService;

import jakarta.websocket.server.PathParam;

/**
 * @author alpha
 *
 */
@Controller
@RequestMapping(value = "/municipality")
public class MunicipalityCtrl extends MainCtrl{
	@Autowired
	private MunicipalityService municipalitySrv;
	
	@GetMapping(value = "/list-municipalities")
	public ResponseEntity<?> listMunicipalities(){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<List<Municipality>> (municipalitySrv.listMunicipalities(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/list-municipalities-by-state/{stateId}")
	public ResponseEntity<?> listMunicipalityByState(@PathVariable(value = "stateId") Long stateId){
		if(!hasAccess()) {			
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(municipalitySrv.listMunicipalityByStateId(stateId), HttpStatus.OK);
	}
	
	@PostMapping(value = "/save-municipality")
	public ResponseEntity<?> saveMunicipality(@RequestBody Municipality municipality){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		Municipality nMunicipality = null;
		try {
			nMunicipality = municipalitySrv.saveMunicipality(municipality);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return new ResponseEntity<>(nMunicipality, HttpStatus.OK);
	}
}

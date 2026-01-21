/**
 * 
 */
package com.alexfierro.league.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexfierro.league.entity.Country;
import com.alexfierro.league.service.CountryService;

/**
 * @author alpha
 *
 */
@RestController
@RequestMapping("/countries")
public class CountryCtrl extends  MainCtrl{
	@Autowired
	private CountryService countrySrv;
	
	@GetMapping("/list-countries")
	public ResponseEntity<?> listUsers(){
		if (!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<List<Country>>(countrySrv.getCountries(), HttpStatus.OK);
	}
	
	@PostMapping("/save-country")
	public ResponseEntity<?> saveCountry(@RequestBody Country country){
		if (!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		Country nCountry = null;
		try {
			nCountry = countrySrv.saveCountry(country);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return new ResponseEntity<>(nCountry, HttpStatus.OK);
	}
	
	@GetMapping("/get-country-byId/{id_country}")
	public ResponseEntity<?> getCountryById(@PathVariable(value = "id_country") Long id_country ) {
		if (!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		HttpStatus status = HttpStatus.OK;
		Country country = null;
		try {
			Optional<Country> countryRecord = countrySrv.findCountryById(id_country);
			if(countryRecord.isPresent()) {
				country = countryRecord.get();
			}else {
				status = HttpStatus.NOT_FOUND;
			}				
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(country, status);
	}
}

/**
 * 
 */
package com.alexfierro.league.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
public class CountryCtrl {
	@Autowired
	private CountryService countrySrv;
	
	@GetMapping("/list-countries")
	public ResponseEntity<List<Country>> listUsers(){
		return new ResponseEntity<List<Country>>(countrySrv.getCountries(), HttpStatus.OK);
	}
	
	@PostMapping("/save-country")
	public ResponseEntity<?> saveCountry(@RequestBody Country country){
		Country nCountry = null;
		try {
			nCountry = countrySrv.saveCountry(country);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return new ResponseEntity<>(nCountry, HttpStatus.OK);
	}
}

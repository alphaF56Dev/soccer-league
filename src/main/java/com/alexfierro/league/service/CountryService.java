/**
 * 
 */
package com.alexfierro.league.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.entity.Country;
import com.alexfierro.league.repository.CountryRepository;

/**
 * @author alpha
 *
 */
@Service
public class CountryService {
	@Autowired
	private CountryRepository countryRep;
	
	public List<Country> getCountries(){
		return countryRep.findAll();
	}
	
	public Country saveCountry(Country country) {
		return countryRep.save(country);
	}
	
	public Optional<Country> getCountryById(Long id_country) {
		return countryRep.findById(id_country);
	}
}

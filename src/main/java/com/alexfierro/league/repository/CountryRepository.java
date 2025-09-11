/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.Country;

/**
 * @author alpha
 *
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long>{
	public Optional<Country> findByName(String country_name);
}

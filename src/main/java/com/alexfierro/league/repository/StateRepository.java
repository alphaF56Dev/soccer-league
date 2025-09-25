/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.State;

/**
 * @author alpha
 *
 */
@Repository
public interface StateRepository extends JpaRepository<State, Long>{
	public Optional<State> findByName(String name);
}

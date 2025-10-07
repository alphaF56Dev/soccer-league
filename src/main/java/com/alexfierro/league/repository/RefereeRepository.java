/**
 * 
 */
package com.alexfierro.league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.Referee;

/**
 * @author alpha
 *
 */
@Repository
public interface RefereeRepository extends JpaRepository<Referee, Long>{
	
}

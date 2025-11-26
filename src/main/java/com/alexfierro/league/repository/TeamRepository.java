/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.Team;

/**
 * @author alpha
 *
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
	public List<Team> findByActive(Boolean active);
	
	public Team findByName(String name);
}

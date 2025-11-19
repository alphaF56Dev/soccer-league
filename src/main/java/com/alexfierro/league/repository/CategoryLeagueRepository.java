/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.CategoryLeague;

/**
 * @author alpha
 *
 */
@Repository
public interface CategoryLeagueRepository extends JpaRepository<CategoryLeague, Long>{
	@Query("SELECT CL FROM CategoryLeague CL WHERE CL.name LIKE '%' || ?1 || '%'")
	public List<CategoryLeague> findCategoryLeagueByName(String name); 
}

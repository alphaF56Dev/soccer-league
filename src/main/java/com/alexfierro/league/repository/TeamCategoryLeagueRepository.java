/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.CategoryLeague;
import com.alexfierro.league.entity.Team;
import com.alexfierro.league.entity.TeamCategoryLeague;

/**
 * @author alpha
 *
 */
@Repository
public interface TeamCategoryLeagueRepository extends JpaRepository<TeamCategoryLeague, Long>{

	@Query("SELECT TC.team FROM TeamCategoryLeague TC WHERE TC.categoryLeague.idCategoryLeague = ?1")
	public List<Team> findByIdCategoryLeague(Long idCategoryLeague);
	
	@Query("SELECT TC.categoryLeague FROM TeamCategoryLeague TC WHERE TC.team.idTeam = ?1")
	public List<CategoryLeague> findByIdTeam(Long idTeam);
	
	@Query("SELECT TC FROM TeamCategoryLeague TC WHERE TC.team.idTeam = ?1 AND TC.categoryLeague.idCategoryLeague = ?2")
	public TeamCategoryLeague findByIdTeamAndIdCategoryLeague(Long idTeam, Long idCategoryLeague);
}

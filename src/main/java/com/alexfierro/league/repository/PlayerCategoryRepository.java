/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alexfierro.league.entity.Player;
import com.alexfierro.league.entity.PlayerCategory;
import com.alexfierro.league.entity.TeamCategoryLeague;

/**
 * @author alpha
 *
 */
public interface PlayerCategoryRepository extends JpaRepository<PlayerCategory, Long>{
	/**
	 * Get all players which belong to the relation between a team and category identified by idTeamCategoryLeague
	 * @param idTeamCategory - id of the relation
	 * @return a list of players found 
	 */
	@Query("SELECT PC.player FROM PlayerCategory PC WHERE PC.teamCategoryLeague.idTeamCategoryLeague = ?1")
	public List<Player> findPlayersByIdTeamCategory(Long idTeamCategory);
	
	/**
	 * Get all teams where a player is linked
	 * @param idPlayer - id of the player to look for their teams linked
	 * @return a list of PlayerCategory if exist.
	 */
	@Query("SELECT PC FROM PlayerCategory PC WHERE PC.player.idPlayer = ?1")
	public List<PlayerCategory> findTeamsByPlayerId(Long idPlayer);
	
	/**
	 * Get all players which belong to the relation between a team and category if we only know the idTeam and idCategoryLeague
	 * @param idTeam - id of the team
	 * @param idCategoryLeague - id of the category league
	 * @return a list of players found if exist.
	 */
	@Query("SELECT PC.player FROM PlayerCategory PC WHERE PC.teamCategoryLeague.team.idTeam = ?1 AND "
			+ "PC.teamCategoryLeague.categoryLeague.idCategoryLeague = ?2")
	public List<Player> findPlayersByIdTeamAndIdCategory(Long idTeam, Long idCategoryLeague);
	
	/**
	 * Get TeamCategory record where we only know idPlayer and idCategoryLeague
	 * @param idPlayer
	 * @param idCategoryLeague
	 * @return TeamCategoryLeague Object if exist
	 */
	@Query("SELECT PC.teamCategoryLeague FROM PlayerCategory PC WHERE PC.player.idPlayer = ?1 AND "
			+ "PC.teamCategoryLeague.categoryLeague.idCategoryLeague = ?2")
	public Optional<TeamCategoryLeague> findTeamCategoryByIdPlayerAndIdCategoryLeague(Long idPlayer, Long idCategoryLeague);
	
	/**
	 * Get TeamCategory identified by idPlayer, idCategoryLeague and idTeam
	 * @param idPlayer
	 * @param idCategoryLeague
	 * @param idTeam
	 * @return TeamCategoryLeague object if exist
	 */
	@Query("SELECT PC.teamCategoryLeague FROM PlayerCategory PC WHERE PC.player.idPlayer = ?1 AND "
			+ "PC.teamCategoryLeague.categoryLeague.idCategoryLeague = ?2 AND "
			+ "PC.teamCategoryLeague.team.idTeam = ?3")
	public Optional<TeamCategoryLeague> findTeamCategoryByIdPlayerAndidCategoryLeagueAndIdTeam(Long idPlayer, Long idCategoryLeague, Long idTeam);
	
	@Query("SELECT PC FROM PlayerCategory PC WHERE PC.player.idPlayer = ?1 AND "
			+ "PC.teamCategoryLeague.categoryLeague.idCategoryLeague = ?2 AND "
			+ "PC.teamCategoryLeague.team.idTeam = ?3")
	public PlayerCategory findByIdPlayerAndidCategoryLeagueAndIdTeam(Long idPlayer, Long idCategoryLeague, Long idTeam);
	
}

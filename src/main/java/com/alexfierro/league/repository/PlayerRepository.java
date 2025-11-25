/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.Player;

/**
 * @author alpha
 *
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
	@Query("SELECT P FROM Player P ")
	public List<Player> findAllPlayerInfo();
	
	@Query("SELECT P FROM Player P WHERE P.member.isActive = ?1")
	public List<Player> findPlayersByStatus(Boolean isActive);
	
	@Query("SELECT P FROM Player P WHERE P.member.idMember = ?1")
	public Player findPlayerByMemberId(Long memberId);
	
	@Query("SELECT P FROM Player P WHERE P.idPlayer = ?1")
	public Player findPlayerByPlayerId(Long playerId);
}

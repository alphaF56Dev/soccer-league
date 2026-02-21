/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.Team;

/**
 * @author alpha
 *
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
	public List<Team> findByIsActive(Boolean isActive);
	
	public Team findByName(String name);
	
	@Query("SELECT T FROM Team T WHERE T.member.idMember = ?1 AND T.isActive = true")
	public Optional<Team> findTeamsByMemberId(Long memberId);
}
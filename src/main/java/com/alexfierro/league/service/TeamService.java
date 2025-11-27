/**
 * 
 */
package com.alexfierro.league.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.controller.LeagueException;
import com.alexfierro.league.entity.Team;
import com.alexfierro.league.entity.dto.TeamDto;
import com.alexfierro.league.repository.TeamRepository;

/**
 * @author alpha
 *
 */
@Service
public class TeamService {
	@Autowired
	private TeamRepository teamRep;
	
	public List<TeamDto> listTeams(){
		return transformToTeamDto(teamRep.findAll());
	}
	
	public List<TeamDto> listTeamsByStatus(Boolean isActive){
		return transformToTeamDto(teamRep.findByIsActive(isActive));
	}
	
	public Team saveTeam(Team team) throws LeagueException{
		Team teamToLookfor = teamRep.findByName(team.getName());
		
		if(teamToLookfor != null && team.getIdTeam() == null) {
			throw new LeagueException(String.format("Already exist a team with the name %s and it could not be repeated.", team.getName()));
		}
		return teamRep.save(team);		
	}
	
	public Optional<Team> getTeamById(Long idTeam) {
		return teamRep.findById(idTeam);
	}
	
	private List<TeamDto> transformToTeamDto(List<Team> teams){
		List<TeamDto> nTeams = new ArrayList<>();
		if(teams != null && teams.size() > 0) {
			for (Team team : teams) {
				nTeams.add(new TeamDto(team));
			}
		}
		return nTeams;
	}
}

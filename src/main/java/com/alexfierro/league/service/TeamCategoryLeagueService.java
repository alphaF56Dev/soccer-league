/**
 * 
 */
package com.alexfierro.league.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.CategoryLeague;
import com.alexfierro.league.entity.Team;
import com.alexfierro.league.entity.TeamCategoryLeague;
import com.alexfierro.league.repository.TeamCategoryLeagueRepository;

/**
 * @author alpha
 *
 */
@Repository
public class TeamCategoryLeagueService {
	@Autowired
	private TeamCategoryLeagueRepository tCatLeagueRep;
	
	public List<Team> listTeamsByIdCategoryLeague(Long idCategoryLeague){
		return tCatLeagueRep.findByIdCategoryLeague(idCategoryLeague);
	}
	
	public List<CategoryLeague> listCategoriesByIdTeam(Long idTeam){
		return tCatLeagueRep.findByIdTeam(idTeam);
	}
	
	/**
	 * This method allows to add new category to Team only if the category has not been added.
	 * @param idTeam
	 * @param idCategoryLeague
	 * @return code to know what happened in the process
	 * - 0: The category has already exists to the team.
	 * - 1: Record added.
	 */
	public int addCategoryToTeam(Long idTeam, Long idCategoryLeague) {
		int code = 0;
		TeamCategoryLeague tCatLeagueToLookfor = tCatLeagueRep.findByIdTeamAndIdCategoryLeague(idTeam, idCategoryLeague);
		if(tCatLeagueToLookfor == null) {
			code = 1;
			TeamCategoryLeague tCatLeagueToSave = new TeamCategoryLeague();
			tCatLeagueToSave.setCategoryLeague(new CategoryLeague());
			tCatLeagueToSave.setTeam(new Team());
			tCatLeagueToSave.getCategoryLeague().setIdCategoryLeague(idCategoryLeague);
			tCatLeagueToSave.getTeam().setIdTeam(idTeam);
			tCatLeagueRep.save(tCatLeagueToSave);
		}
		return code;
	}
	
	/**
	 * This method allows you to remove a category from a team. 
	 * @param idTeam
	 * @param idCategoryLeague
	 * @return Code to know what happened in the process
	 * - 0: category is not added to the team
	 * - 1: record removed
	 */
	public int removeCategoryOfTeam(Long idTeam, Long idCategoryLeague) {
		int code = 0;
		TeamCategoryLeague tCatLeagueToLookfor = tCatLeagueRep.findByIdTeamAndIdCategoryLeague(idTeam, idCategoryLeague);
		if(tCatLeagueToLookfor != null) {
			code = 1;
			tCatLeagueRep.delete(tCatLeagueToLookfor);
		}
		return code;
	}
}

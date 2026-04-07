/**
 * 
 */
package com.alexfierro.league.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.CategoryLeague;
import com.alexfierro.league.entity.Team;
import com.alexfierro.league.entity.TeamCategoryLeague;
import com.alexfierro.league.entity.dto.TeamCategoryDto;
import com.alexfierro.league.repository.TeamCategoryLeagueRepository;

/**
 * @author alpha
 *
 */
@Repository
public class TeamCategoryLeagueService {
	@Autowired
	private TeamCategoryLeagueRepository tCatLeagueRep;
	
	@Autowired
	private CategoryLeagueService catLeagueSrv;
	
	public List<Team> listTeamsByIdCategoryLeague(Long idCategoryLeague){
		return tCatLeagueRep.findByIdCategoryLeague(idCategoryLeague);
	}
	
	public List<CategoryLeague> listCategoriesByIdTeam(Long idTeam){
		return tCatLeagueRep.findByIdTeam(idTeam);
	}
	
	public List<TeamCategoryDto> getListCategoriesByTeam(Long idTeam){
		List<Long> categoriesId = getListCategoriesIdByIdTeam(idTeam);
		List<CategoryLeague> allCategories = catLeagueSrv.listCategoryLeague();
		List<TeamCategoryDto> teamCategories = new ArrayList<>();
		
		if(categoriesId != null && categoriesId.size() > 0) {			
			for(CategoryLeague category : allCategories) {
				Boolean isChecked = false;
				if(categoriesId != null && categoriesId.size() > 0) {
					for (int i = 0; i < categoriesId.size(); i++) {
						if(category.getIdCategoryLeague() == categoriesId.get(i)) {
							isChecked = true;
							categoriesId.remove(i);							
							break;
						}
					}
				}
				teamCategories.add(new TeamCategoryDto(category, isChecked));
			}
		}else {
			for (int i = 0; i < allCategories.size(); i++) {
				teamCategories.add(new TeamCategoryDto(allCategories.get(i), false));
			}
		}
		return teamCategories;
	}
	
	private List<Long> getListCategoriesIdByIdTeam(Long idTeam){
		return tCatLeagueRep.findCategoriesIdByIdTeam(idTeam);
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

/**
 * 
 */
package com.alexfierro.league.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.entity.Player;
import com.alexfierro.league.entity.PlayerCategory;
import com.alexfierro.league.entity.TeamCategoryLeague;
import com.alexfierro.league.entity.dto.PlayerCategoryDto;
import com.alexfierro.league.repository.PlayerCategoryRepository;
import com.alexfierro.league.repository.PlayerRepository;
import com.alexfierro.league.repository.TeamCategoryLeagueRepository;

/**
 * @author alpha
 *
 */
@Service
public class PlayerCategoryService {
	@Autowired
	private PlayerCategoryRepository playerCatRep;
	
	@Autowired
	private TeamCategoryLeagueRepository teamCatLeagueRep;
	
	@Autowired
	private PlayerRepository playerRep;
	
	public List<Player> getPlayersByIdTeamCategory(Long idTeamCategoryLeague){
		return playerCatRep.findPlayersByIdTeamCategory(idTeamCategoryLeague);
	}
	
	public List<Player> getPlayersByIdTeamAndIdCategory(Long idTeam, Long idCategoryLeague){
		return playerCatRep.findPlayersByIdTeamAndIdCategory(idTeam, idCategoryLeague);
	}
	
	public List<PlayerCategoryDto> getTeamsByIdPlayer(Long idPlayer){
		return transformToListPlayerCategoryDto(playerCatRep.findTeamsByPlayerId(idPlayer));
	}
	
	private List<PlayerCategoryDto> transformToListPlayerCategoryDto(List<PlayerCategory> playerCatList){
		List<PlayerCategoryDto> nPlayerCatList =  new ArrayList<>();
		for (PlayerCategory playerCategory : playerCatList) {
			nPlayerCatList.add(new PlayerCategoryDto(playerCategory));
		}
		return nPlayerCatList;
	}
	
	/**
	 * This method save an PlayerCategory object, before the insertion it's validated the player:
	 * - Not exist in a team inside the same category league
	 * - Not exist a previous record with new information to save
	 * - Is necessary older for the category
	 * @param idPlayer
	 * @param idTeam
	 * @param idCategoryLeague
	 * @param nickname
	 * @param player_number
	 * @return code to know what happen in the process
	 * - 0: The player has already linked to this team in the same category.
	 * - 1: Record added.
	 * - 2: The player's age is not valid for the category selected.
	 * - 3: The player has already linked to another team in the same category.
	 */
	public int savePlayerCategory(Long idPlayer, Long idTeam, Long idCategoryLeague, short player_number) {
		int code = 1;
		Period currentAge;		
		PlayerCategory playerCat = playerCatRep.findByIdPlayerAndidCategoryLeagueAndIdTeam(idPlayer, idCategoryLeague, idTeam);
		
		if(playerCat == null) {
			playerCat = new PlayerCategory();
			TeamCategoryLeague teamCat = teamCatLeagueRep.findByIdTeamAndIdCategoryLeague(idTeam, idCategoryLeague);
			playerCat.setTeamCategoryLeague(teamCat);
			Player player = playerRep.findPlayerByPlayerId(idPlayer);
			playerCat.setPlayer(player);
		}
				
		Calendar calendar = Calendar.getInstance();
		Date now = new Date(calendar.getTimeInMillis());
		playerCat.setPlayerNumber(player_number);
		playerCat.setRegistrationDate(now);
		currentAge = Period.between(playerCat.getPlayer().getMember().getBirthday().toLocalDate(), LocalDate.now()); 
		
		Optional<TeamCategoryLeague> catLeague1 = playerCatRep.findTeamCategoryByIdPlayerAndidCategoryLeagueAndIdTeam(
				idPlayer,
				idCategoryLeague,
				idTeam
				);
		
		Optional<TeamCategoryLeague> catLeague2 = playerCatRep.findTeamCategoryByIdPlayerAndIdCategoryLeague(
				idPlayer,
				idCategoryLeague
				);
		
		if(catLeague1.isPresent() && playerCat.getIdPlayerCategory() == null && !playerCat.getTeamCategoryLeague().equals(catLeague1.get())) {
			code = 0;
		}else if(currentAge.getYears() < playerCat.getTeamCategoryLeague().getCategoryLeague().getMinAge() ||	
				currentAge.getYears() > playerCat.getTeamCategoryLeague().getCategoryLeague().getMaxAge()){
			code = 2;
		}else if(catLeague2.isPresent() && playerCat.getIdPlayerCategory() == null) {		
			code = 3;
		}else{
			code = 1;
			playerCatRep.save(playerCat);
		}
		return code;
	}
	
	public void removeByIdPlayerCategory(Long idPlayerCategory) {
		PlayerCategory playerCat = playerCatRep.findById(idPlayerCategory).get();
		playerCatRep.delete(playerCat);
	}
	
	/**
	 * This method allows delete a PlayerCategory record if we know idPlayer, idTeam and idCategory.
	 * @param idPlayer
	 * @param idTeam
	 * @param idCategoryLeague
	 * @return Code to know what happen in the process
	 * - 0: Record not found
	 * - 1: Record deleted  
	 */
	public int removeByIdPlayerAndIdTeamAndIdCategory(Long idPlayer, Long idTeam, Long idCategoryLeague) {
		int code = 0;
		PlayerCategory playerCat = playerCatRep.findByIdPlayerAndidCategoryLeagueAndIdTeam(idPlayer, idCategoryLeague, idTeam);
		if(playerCat != null) {
			code = 1;
			playerCatRep.delete(playerCat);
		}
		return code;
	}
}

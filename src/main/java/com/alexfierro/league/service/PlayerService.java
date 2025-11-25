/**
 * 
 */
package com.alexfierro.league.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.controller.LeagueException;
import com.alexfierro.league.entity.Player;
import com.alexfierro.league.entity.dto.PlayerDto;
import com.alexfierro.league.repository.PlayerRepository;

/**
 * @author alpha
 *
 */
@Service
public class PlayerService {
	@Autowired
	private PlayerRepository playerRep;
	
	public List<PlayerDto> listPlayers(){
		return transfortToListPlayerDto(playerRep.findAllPlayerInfo()) ;
	}
	
	public List<PlayerDto> listPlayerByStatus(Boolean isActive){
		return transfortToListPlayerDto(playerRep.findPlayersByStatus(isActive));
	}
	
	public PlayerDto getPlayerByMemberId(Long idMember) {
		return transformToPlayerDto(playerRep.findPlayerByMemberId(idMember));
	}
	
	public PlayerDto getPlayerByPlayerId(Long idPlayer) {
		return transformToPlayerDto(playerRep.findPlayerByPlayerId(idPlayer));
	}
	
	public Player savePlayer(Player player) throws LeagueException {
		Player playerToLookfor = playerRep.findPlayerByMemberId(player.getMember().getIdMember());
		if(playerToLookfor != null) {
			throw new LeagueException("Player information could not be saved due to the member selected has already information player assigned.");
		}
		return playerRep.save(player);
	}
	
	private List<PlayerDto> transfortToListPlayerDto(List<Player> players){
		List<PlayerDto> nPlayerList = new ArrayList<>();
		if(players != null && players.size() > 0) {
			for (Player player : players) {
				nPlayerList.add(new PlayerDto(player));
			}
		}
		return nPlayerList;
	}
	
	private PlayerDto transformToPlayerDto(Player player) {
		PlayerDto nPlayer = null;
		if(player != null) {
			nPlayer = new PlayerDto(player);
		}
		return nPlayer;
	}
}

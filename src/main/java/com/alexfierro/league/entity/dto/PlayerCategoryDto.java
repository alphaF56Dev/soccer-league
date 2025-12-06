/**
 * 
 */
package com.alexfierro.league.entity.dto;

import java.sql.Date;

import com.alexfierro.league.entity.PlayerCategory;

/**
 * @author alpha
 *
 */
public class PlayerCategoryDto {
	private Long idPlayerCategory;
	
	private Long idTeamCategory;
	private String teamName;
	private String categoryName;
	private short minAge;
	private short maxAge;
	
	private Long playerId;
	private String nickname;
	
	private Date registration_date;
	private short player_number;
	
	public PlayerCategoryDto(PlayerCategory playerCategory) {
		idPlayerCategory = playerCategory.getIdPlayerCategory();
		
		idTeamCategory = playerCategory.getTeamCategoryLeague().getIdTeamCategoryLeague();
		teamName = playerCategory.getTeamCategoryLeague().getTeam().getName();
		categoryName = playerCategory.getTeamCategoryLeague().getCategoryLeague().getName();
		minAge = playerCategory.getTeamCategoryLeague().getCategoryLeague().getMinAge();
		maxAge = playerCategory.getTeamCategoryLeague().getCategoryLeague().getMaxAge();
		
		playerId = playerCategory.getPlayer().getIdPlayer();
		nickname = playerCategory.getPlayer().getNickname();
		
		registration_date = playerCategory.getRegistrationDate();
		player_number = playerCategory.getPlayerNumber();
	}
	
	public Long getIdPlayerCategory() {
		return idPlayerCategory;
	}
	public void setIdPlayerCategory(Long idPlayerCategory) {
		this.idPlayerCategory = idPlayerCategory;
	}
	public Long getIdTeamCategory() {
		return idTeamCategory;
	}
	public void setIdTeamCategory(Long idTeamCategory) {
		this.idTeamCategory = idTeamCategory;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public short getMinAge() {
		return minAge;
	}
	public void setMinAge(short minAge) {
		this.minAge = minAge;
	}
	public short getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(short maxAge) {
		this.maxAge = maxAge;
	}
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	public short getPlayer_number() {
		return player_number;
	}
	public void setPlayer_number(short player_number) {
		this.player_number = player_number;
	}
}

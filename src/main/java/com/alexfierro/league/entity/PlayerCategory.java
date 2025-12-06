/**
 * 
 */
package com.alexfierro.league.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author alpha
 *
 */
@Entity
@Table(name = "player_category")
public class PlayerCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_player_category")
	private Long idPlayerCategory;
	
	@ManyToOne
	@JoinColumn(name="id_team_category_league", referencedColumnName = "id_team_category_league")
	private TeamCategoryLeague teamCategoryLeague;
	
	@ManyToOne
	@JoinColumn(name="id_player", referencedColumnName = "id_player")
	private Player player;
	
	@Column(name="registration_date")
	private Date registrationDate;
	
	@Column(name="player_number")
	private short playerNumber;

	public Long getIdPlayerCategory() {
		return idPlayerCategory;
	}

	public void setIdPlayerCategory(Long idPlayerCategory) {
		this.idPlayerCategory = idPlayerCategory;
	}

	public TeamCategoryLeague getTeamCategoryLeague() {
		return teamCategoryLeague;
	}

	public void setTeamCategoryLeague(TeamCategoryLeague teamCategoryLeague) {
		this.teamCategoryLeague = teamCategoryLeague;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public short getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(short playerNumber) {
		this.playerNumber = playerNumber;
	}
}

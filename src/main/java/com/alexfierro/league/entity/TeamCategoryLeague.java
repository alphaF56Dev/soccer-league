/**
 * 
 */
package com.alexfierro.league.entity;

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
@Table(name = "team_category_league")
public class TeamCategoryLeague {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_team_category_league")
	private Long idTeamCategoryLeague;
	
	@ManyToOne
	@JoinColumn(name="id_team", referencedColumnName = "id_team")
	private Team team;
	
	@ManyToOne
	@JoinColumn(name="id_category_league", referencedColumnName = "id_category_league")
	private CategoryLeague categoryLeague;

	public Long getIdTeamCategoryLeague() {
		return idTeamCategoryLeague;
	}

	public void setIdTeamCategoryLeague(Long idTeamCategoryLeague) {
		this.idTeamCategoryLeague = idTeamCategoryLeague;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public CategoryLeague getCategoryLeague() {
		return categoryLeague;
	}

	public void setCategoryLeague(CategoryLeague categoryLeague) {
		this.categoryLeague = categoryLeague;
	}
}

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
@Table(name="category_league_has_referee")
public class CategoryReferee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_category_referee")
	private Long idCategoryReferee;
	
	@ManyToOne
	@JoinColumn(name = "id_category_league", referencedColumnName = "id_category_league")
	private CategoryLeague idCategoryLeague;
	
	@ManyToOne
	@JoinColumn(name="id_referee", referencedColumnName = "id_referee")
	private Referee idReferee;

	public CategoryLeague getIdCategoryLeague() {
		return idCategoryLeague;
	}

	public void setIdCategoryLeague(CategoryLeague idCategoryLeague) {
		this.idCategoryLeague = idCategoryLeague;
	}

	public Referee getIdReferee() {
		return idReferee;
	}

	public void setIdReferee(Referee idReferee) {
		this.idReferee = idReferee;
	}

	public Long getIdCategoryReferee() {
		return idCategoryReferee;
	}

	public void setIdCategoryReferee(Long idCategoryReferee) {
		this.idCategoryReferee = idCategoryReferee;
	}
}

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
@Table(name = "field_category")
public class FieldCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_field_category")
	private Long idFieldCategory;
	
	@ManyToOne
	@JoinColumn(name = "id_soccer_field", referencedColumnName = "id_soccer_field")
	private Field idField;
	
	@ManyToOne
	@JoinColumn(name = "id_category_league", referencedColumnName = "id_category_league")
	private CategoryLeague idCategoryLeague;
	
	/**
	 * This value must be in 24h format to have an standar data
	 */
	@Column(name = "start_hour")
	private String startHour;
	
	/**
	 * This value must be in 24h format to have an standar data
	 */	
	@Column(name = "end_hour")
	private String endHour;
	
	/**
	 * the value only will include values from 1234567, which 1 is Monday and 7 is Sunday
	 */
	@Column(name = "match_days") 
	private String matchDays;
	
	@Column(name = "isActive", nullable = true)
	private boolean isActive;

	public Long getIdFieldCategory() {
		return idFieldCategory;
	}

	public void setIdFieldCategory(Long idFieldCategory) {
		this.idFieldCategory = idFieldCategory;
	}

	public Field getIdField() {
		return idField;
	}

	public void setIdField(Field idField) {
		this.idField = idField;
	}

	public CategoryLeague getIdCategoryLeague() {
		return idCategoryLeague;
	}

	public void setIdCategoryLeague(CategoryLeague idCategoryLeague) {
		this.idCategoryLeague = idCategoryLeague;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getMatchDays() {
		return matchDays;
	}

	public void setMatchDays(String matchDays) {
		this.matchDays = matchDays;
	}
	
	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "FieldCategory [idFieldCategory=" + idFieldCategory + ", idField=" + idField + ", idCategoryLeague="
				+ idCategoryLeague + ", startHour=" + startHour + ", endHour=" + endHour + ", matchDays=" + matchDays
				+ ", isActive=" + isActive + "]";
	}
}

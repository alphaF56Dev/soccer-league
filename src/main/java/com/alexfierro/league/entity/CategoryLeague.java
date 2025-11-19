/**
 * 
 */
package com.alexfierro.league.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author alpha
 *
 */
@Entity
@Table(name="category_league")
public class CategoryLeague {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="id_category_league")
	private Long idCategoryLeague;
	
	@Column(name="category_name", nullable = false)
	private String name;
	
	@Column(name="isactive", nullable = false)
	private Boolean isActive;
	
	@Column(name="minage", nullable = false)
	private short minAge;
	
	@Column(name ="maxage", nullable = false)
	private short maxAge;
	
	@Column(name="description", nullable = false)
	private String description;
	
	@Column(name="duration", nullable = false)
	private short duration;

	public Long getIdCategoryLeague() {
		return idCategoryLeague;
	}

	public void setIdCategoryLeague(Long idCategoryLeague) {
		this.idCategoryLeague = idCategoryLeague;
	}

	public String getName() {
		return name;
	}

	public void setName(String categoryName) {
		this.name = categoryName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short getDuration() {
		return duration;
	}

	public void setDuration(short duration) {
		this.duration = duration;
	}
}
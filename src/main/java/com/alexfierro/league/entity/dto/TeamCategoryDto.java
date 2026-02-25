/**
 * 
 */
package com.alexfierro.league.entity.dto;

import com.alexfierro.league.entity.CategoryLeague;

/**
 * @author alpha
 *
 */
public class TeamCategoryDto {
	private Long idCategoryLeague;
	private String name;
	private boolean isActive;
	private int minAge;
	private int maxAge;
	private int duration;
	private Character sex;
	private boolean checked;
	
	
	
	public TeamCategoryDto(CategoryLeague category, boolean checked) {
		super();
		this.idCategoryLeague = category.getIdCategoryLeague();
		this.name = category.getName();
		this.isActive = category.getIsActive();
		this.minAge = category.getMinAge();
		this.maxAge = category.getMaxAge();
		this.duration = category.getDuration();
		this.sex = category.getSex();
		this.checked = checked;
	}
	
	public Long getIdCategoryLeague() {
		return idCategoryLeague;
	}
	public void setIdCategoryLeague(Long idCategoryLeague) {
		this.idCategoryLeague = idCategoryLeague;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getMinAge() {
		return minAge;
	}
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	public int getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Character getSex() {
		return sex;
	}
	public void setSex(Character sex) {
		this.sex = sex;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}	
}

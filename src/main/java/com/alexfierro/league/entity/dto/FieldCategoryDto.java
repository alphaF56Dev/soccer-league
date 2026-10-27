/**
 * 
 */
package com.alexfierro.league.entity.dto;

import com.alexfierro.league.entity.FieldCategory;

/**
 * @author alpha
 *
 */
public class FieldCategoryDto {
	private Long idFieldCategory;
	
	private Long idField;
	private String nameField;
	
	private Long idCategoryLeague;
	private String categoryName;
	private String age;
	private String sex;
	
	private String startHour;
	private String endHour;
	private String matchDays;
	
	public FieldCategoryDto(FieldCategory fieldCategory) {
		idFieldCategory = fieldCategory.getIdFieldCategory();
		
		idField = fieldCategory.getIdField().getIdField();
		nameField = fieldCategory.getIdField().getName();
		
		idCategoryLeague = fieldCategory.getIdCategoryLeague().getIdCategoryLeague();
		categoryName = fieldCategory.getIdCategoryLeague().getName();
		age = fieldCategory.getIdCategoryLeague().getMinAge() + "-" + fieldCategory.getIdCategoryLeague().getMaxAge();
		sex = fieldCategory.getIdCategoryLeague().getSex().toString();
		
		startHour = fieldCategory.getStartHour();
		endHour = fieldCategory.getEndHour();
		matchDays = fieldCategory.getMatchDays();
	}
	
	public Long getIdFieldCategory() {
		return idFieldCategory;
	}
	public void setIdFieldCategory(Long idFieldCategory) {
		this.idFieldCategory = idFieldCategory;
	}
	public Long getIdField() {
		return idField;
	}
	public void setIdField(Long idField) {
		this.idField = idField;
	}
	public String getNameField() {
		return nameField;
	}
	public void setNameField(String nameField) {
		this.nameField = nameField;
	}
	public Long getIdCategoryLeague() {
		return idCategoryLeague;
	}
	public void setIdCategoryLeague(Long idCategoryLeague) {
		this.idCategoryLeague = idCategoryLeague;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
}

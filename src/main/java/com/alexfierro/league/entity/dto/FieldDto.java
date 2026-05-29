/**
 * 
 */
package com.alexfierro.league.entity.dto;

import com.alexfierro.league.entity.Field;

/**
 * @author alpha
 *
 */
public class FieldDto {
	private Long idField;
	private String municipalityName;
	private String name;
	private String address;
	private Boolean isActive;
	
	
	public FieldDto(Field field) {
		idField = field.getIdField();
		municipalityName = field.getIdMunicipality().getName() + "," 
				+ (field.getIdMunicipality().getIdState() != null ? field.getIdMunicipality().getIdState().getShortName() : "");
		name = field.getName();
		address = field.getAddress();
		isActive = field.getIsActive();
	}
	public Long getIdField() {
		return idField;
	}
	public void setIdField(Long idField) {
		this.idField = idField;
	}
	public String getMunicipalityName() {
		return municipalityName;
	}
	public void setMunicipalityName(String municipalityName) {
		this.municipalityName = municipalityName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "FieldDto [idField=" + idField + ", municipalityName=" + municipalityName + ", name=" + name
				+ ", address=" + address + ", isActive=" + isActive + "]";
	}	
}

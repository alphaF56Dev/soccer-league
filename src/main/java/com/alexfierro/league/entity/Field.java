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
@Table(name = "soccer_field")
public class Field {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_soccer_field")
	private Long idField;
	
	@ManyToOne
	@JoinColumn(name = "id_municipality", referencedColumnName = "id_municipality")
	private Municipality idMunicipality;
	
	@Column(name = "field_name")
	private String name;
	
	@Column(name = "full_address")
	private String address;
	
	@Column(name = "isActive")
	private  Boolean isActive;

	public Long getIdField() {
		return idField;
	}

	public void setIdField(Long idField) {
		this.idField = idField;
	}

	public Municipality getIdMunicipality() {
		return idMunicipality;
	}

	public void setIdMunicipality(Municipality idMunicipality) {
		this.idMunicipality = idMunicipality;
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
}

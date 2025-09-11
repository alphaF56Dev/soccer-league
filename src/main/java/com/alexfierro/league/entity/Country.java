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
@Table(name = "country")
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_country;
	
	@Column(nullable = false, name = "country_name")
	private String name; 
	
	@Column(nullable = false, name = "iso_code")
	private String iso;

	public Long getId_country() {
		return id_country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public void setId_country(Long id_country) {
		this.id_country = id_country;
	}	
}

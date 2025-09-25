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
@Table(name = "state")
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_state;
	
	@ManyToOne
	@JoinColumn(name = "id_country", referencedColumnName = "id_country")
	private Country idCountry;
	
	@Column(name="state_name")
	private String name;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "short_name")
	private String shortName;

	public Long getId_state() {
		return id_state;
	}

	public void setId_state(Long id_state) {
		this.id_state = id_state;
	}

	public Country getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(Country idCountry) {
		this.idCountry = idCountry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}

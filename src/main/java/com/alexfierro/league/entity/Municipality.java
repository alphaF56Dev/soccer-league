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
@Table(name = "Municipality")
public class Municipality {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_municipality")
	private Long idMunicipality;
	
	@ManyToOne
	@JoinColumn(name = "id_state", referencedColumnName = "id_state", nullable = false)
	private State idState;
	
	@Column(name="municipality_name", nullable = false)
	private String name;
	
	@Column(name = "short_name")
	private String shortName;

	public Long getIdMunicipality() {
		return idMunicipality;
	}

	public void setIdMunicipality(Long idMunicipality) {
		this.idMunicipality = idMunicipality;
	}

	public State getIdState() {
		return idState;
	}

	public void setIdState(State idState) {
		this.idState = idState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}

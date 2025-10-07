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
@Table(name = "Referee")
public class Referee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_referee")
	private Long idReferee;
	
	@ManyToOne
	@JoinColumn(name="id_member", referencedColumnName = "id_member")
	private Member member;
	
	@Column(name="isActive")
	private Boolean isActive;

	public Long getIdReferee() {
		return idReferee;
	}

	public void setIdReferee(Long idReferee) {
		this.idReferee = idReferee;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
}

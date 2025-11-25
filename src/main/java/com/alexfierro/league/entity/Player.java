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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * @author alpha
 *
 */
@Entity
@Table
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_player")
	private Long idPlayer;
	
	@OneToOne
	@JoinColumn(name = "idposition_catalog", referencedColumnName = "idposition_catalog")
	private PositionCatalog position;
	
	@OneToOne
	@JoinColumn(name="id_member", referencedColumnName = "id_member")
	private Member member;
	
	@Column(name="nickname")
	private String nickname;

	public Long getIdPlayer() {
		return idPlayer;
	}

	public void setIdPLayer(Long idPLayer) {
		this.idPlayer = idPLayer;
	}

	public PositionCatalog getPosition() {
		return position;
	}

	public void setPosition(PositionCatalog position) {
		this.position = position;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}

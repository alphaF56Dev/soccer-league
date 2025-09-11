package com.alexfierro.league.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_access")
public class UserAccess {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_user_access")
	private Long idUser;
	
	@Column
	private String username;
	
	@Column(name = "pwd")
	private String password;
	
	@Column
	private Boolean isActive;
	
	@Column(name = "registration_date")
	private Timestamp registrationDate;
	
	@Column(name = "blockedOn")
	private Timestamp blockedOn;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Timestamp getBlockedOn() {
		return blockedOn;
	}

	public void setBlockedOn(Timestamp blockedOn) {
		this.blockedOn = blockedOn;
	}
	
	
}

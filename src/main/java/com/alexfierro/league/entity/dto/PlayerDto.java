/**
 * 
 */
package com.alexfierro.league.entity.dto;

import java.sql.Date;

import com.alexfierro.league.entity.Player;

/**
 * @author alpha
 *
 */

public class PlayerDto {
	private Long idPlayer;
	private String nickname;
	
	private Long idMember;
	private String name;
	private String personalId;
	private Date birthday;
	private String phoneNumber;
	private Character sex;
	private String email;
	private Boolean isActive;
	private String nationality;
	
	private int idPositionCatalog;
	private String positionName;
	private String positionCode;
	
	public PlayerDto(Player player) {
		idPlayer = player.getIdPlayer();
		nickname = player.getNickname();
		
		idMember = player.getMember().getIdMember();
		name = player.getMember().getName();
		personalId = player.getMember().getPersonalId();
		birthday = player.getMember().getBirthday();
		phoneNumber = player.getMember().getPhone();
		sex = player.getMember().getSex();
		email = player.getMember().getEmail();
		isActive = player.getMember().getIsActive();
		nationality = player.getMember().getNationality();
		
		idPositionCatalog = player.getPosition().getIdPosition();
		positionName = player.getPosition().getName();
		positionCode = player.getPosition().getCode();
		
	}

	public Long getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Long idPlayer) {
		this.idPlayer = idPlayer;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Long getIdMember() {
		return idMember;
	}

	public void setIdMember(Long idMember) {
		this.idMember = idMember;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getIdPositionCatalog() {
		return idPositionCatalog;
	}

	public void setIdPositionCatalog(int idPositionCatalog) {
		this.idPositionCatalog = idPositionCatalog;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
}

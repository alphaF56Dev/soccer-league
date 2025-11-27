/**
 * 
 */
package com.alexfierro.league.entity.dto;

import java.sql.Date;

import com.alexfierro.league.entity.Team;

/**
 * @author alpha
 *
 */
public class TeamDto {
	private Long idTeam;
	private String name;
	private Date registrationDate;
	private Boolean isActive;
	
	private Long idMember;
	private String memberName;
	private String personalId;
	private Date birthday;
	private String phoneNumber;
	private Character sex;
	private String email;
	private Boolean memberIsActive;
	private String nationality;
	
	public TeamDto(Team team) {
		idTeam = team.getIdTeam();
		name = team.getName();
		registrationDate = team.getRegistrationDate();
		isActive = team.getIsActive();
		
		idMember = team.getMember().getIdMember();
		memberName = team.getMember().getName();
		personalId = team.getMember().getPersonalId();
		birthday = team.getMember().getBirthday();
		phoneNumber = team.getMember().getPhone();
		sex = team.getMember().getSex();
		email = team.getMember().getEmail();
		memberIsActive = team.getMember().getIsActive();
		nationality = team.getMember().getNationality();
	}
	
	public Long getIdTeam() {
		return idTeam;
	}
	public void setIdTeam(Long idTeam) {
		this.idTeam = idTeam;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Long getIdMember() {
		return idMember;
	}
	public void setIdMember(Long idMember) {
		this.idMember = idMember;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
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
	public Boolean getMemberIsActive() {
		return memberIsActive;
	}
	public void setMemberIsActive(Boolean memberIsActive) {
		this.memberIsActive = memberIsActive;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	
}

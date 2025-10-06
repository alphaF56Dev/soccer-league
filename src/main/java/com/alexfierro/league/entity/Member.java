/**
 * 
 */
package com.alexfierro.league.entity;

import java.sql.Date;

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
@Table(name = "member")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_member")
	private Long idMember;
	
	@ManyToOne
	@JoinColumn(name="id_member_type", referencedColumnName = "id_member_type")
	private MemberType memberType;
	
	@Column(name="name_member", nullable = false)
	private String name;
	
	@Column(name="personal_id", nullable = false)
	private String personalId;
	
	@Column(name="birthday", nullable = false)
	private Date birthday;
	
	@Column(name="phone_number")
	private String phone;
	
	@Column(name ="sex", nullable=false)
	private Character sex;
	
	@Column(name="email")
	private String email;
	
	@Column(name="isActive")
	private Boolean isActive;
	
	@Column(name="nationality", nullable = false)
	private String nationality;

	public Long getIdMember() {
		return idMember;
	}

	public void setIdMember(Long idMember) {
		this.idMember = idMember;
	}

	public MemberType getMemberType() {
		return memberType;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
}

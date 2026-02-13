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
@Table(name="address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_address")
	private Long idAddress;
	
	@Column(name= "address_name")
	private String addressName;
	
	@Column(name="street")
	private String street;
	
	@Column(name="ext_number")
	private String extNumber;
	
	@Column(name="int_number")
	private String intNumber;
	
	@Column(name="suburb")
	private String suburb;
	
	@Column(name="zip")
	private String zip;
	
	@Column(name="full_address")
	private String fullAddress;
	
	@ManyToOne
	@JoinColumn(name = "id_municipality", referencedColumnName = "id_municipality")
	private Municipality idMunicipality;
	
	@ManyToOne
	@JoinColumn(name = "id_member", referencedColumnName = "id_member")
	private Member idMember;

	public Long getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(Long idAddress) {
		this.idAddress = idAddress;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getExtNumber() {
		return extNumber;
	}

	public void setExtNumber(String extNumber) {
		this.extNumber = extNumber;
	}

	public String getIntNumber() {
		return intNumber;
	}

	public void setIntNumber(String intNumber) {
		this.intNumber = intNumber;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public Municipality getIdMunicipality() {
		return idMunicipality;
	}

	public void setIdMunicipality(Municipality idMunicipality) {
		this.idMunicipality = idMunicipality;
	}

	public Member getIdMember() {
		return idMember;
	}

	public void setIdMember(Member idMember) {
		this.idMember = idMember;
	}
}

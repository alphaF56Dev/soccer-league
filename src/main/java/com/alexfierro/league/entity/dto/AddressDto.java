/**
 * 
 */
package com.alexfierro.league.entity.dto;

/**
 * @author alpha
 *
 */
public class AddressDto {
	private Long idAddress;
	private String addressName;
	private String suburb;
	private String location;
	
	
	
	public AddressDto(Long idAddress, String addressName, String suburb, String location) {
		this.idAddress = idAddress;
		this.addressName = addressName;
		this.suburb = suburb;
		this.location = location;
	}
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
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburd) {
		this.suburb = suburd;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}

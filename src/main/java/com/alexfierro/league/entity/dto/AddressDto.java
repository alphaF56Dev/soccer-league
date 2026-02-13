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
	private String suburd;
	private String location;
	
	
	
	public AddressDto(Long idAddress, String addressName, String suburd, String location) {
		this.idAddress = idAddress;
		this.addressName = addressName;
		this.suburd = suburd;
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
	public String getSuburd() {
		return suburd;
	}
	public void setSuburd(String suburd) {
		this.suburd = suburd;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}

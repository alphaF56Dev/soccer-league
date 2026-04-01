/**
 * 
 */
package com.alexfierro.league.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.entity.Address;
import com.alexfierro.league.entity.dto.AddressDto;
import com.alexfierro.league.repository.AddressRepository;

/**
 * @author alpha
 *
 */
@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRep;
	
	public List<AddressDto> findAllAddressByMemberId(Long memberId){
		return transformToAddressDto(addressRep.findAllByMemberId(memberId));
	}
	
	public Address saveAddress(Address address) {
		return addressRep.save(address);
	}
	
	public Optional<Address> findAddresbyId(Long addressId) {
		return addressRep.findById(addressId);
	}
	
	private List<AddressDto> transformToAddressDto(List<Address> addresses){
		List<AddressDto> nAddressList = new ArrayList<>();
		if(addresses != null) {
			for (Address address : addresses) {
				nAddressList.add(new AddressDto(
						address.getIdAddress(), 
						address.getAddressName(), 
						address.getSuburb(), 
						address.getIdMunicipality().getIdState().getCode()+ "-" + address.getIdMunicipality().getName())
						);
			}
		}
		return nAddressList;
	}
}

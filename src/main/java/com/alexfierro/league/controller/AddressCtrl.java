/**
 * 
 */
package com.alexfierro.league.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexfierro.league.entity.Address;
import com.alexfierro.league.entity.dto.AddressDto;
import com.alexfierro.league.service.AddressService;

/**
 * @author alpha
 *
 */
@RestController
@RequestMapping(value = "/address")
public class AddressCtrl extends MainCtrl{
	
	@Autowired
	private AddressService addressSrv;
	
	@GetMapping(value = "/get-address-by-idMember/{idMember}")
	public ResponseEntity<?> getAddressByIdMember(@PathVariable(value = "idMember") Long idMember){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		List<AddressDto> addressesDto = new ArrayList<AddressDto>();
		HttpStatus status = HttpStatus.OK;
		try {
			addressesDto = addressSrv.findAllAddressByMemberId(idMember);
			if (addressesDto.size() == 0) {
				status = HttpStatus.NO_CONTENT;
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(addressesDto, status);
	}
	
	@PostMapping(value = "/save-address")
	public ResponseEntity<?> saveAddress(@RequestBody Address address){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		Address nAddress = null;
		try {
			address.setFullAddress(address.getStreet() + " " + address.getExtNumber() + " " + address.getIntNumber()
					+ " " + address.getSuburb() + " C.P." + address.getZip());
			nAddress = addressSrv.saveAddress(address);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(nAddress, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-address-byid/{addresId}")
	public ResponseEntity<?> getAddressById(@PathVariable(value = "addresId") Long addresId){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		Address addressToLookFor = null;
		HttpStatus status = HttpStatus.OK;
		try {
			Optional<Address> addressFound = addressSrv.findAddresbyId(addresId);
			if(addressFound.isPresent()) {
				addressToLookFor = addressFound.get();
			}else {
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(addressToLookFor, status);
	}
}

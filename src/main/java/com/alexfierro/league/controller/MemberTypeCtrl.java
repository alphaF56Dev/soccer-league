/**
 * 
 */
package com.alexfierro.league.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alexfierro.league.entity.MemberType;
import com.alexfierro.league.service.MemberTypeService;

/**
 * @author alpha
 *
 */
@Controller
@RequestMapping(value="/member-type")
public class MemberTypeCtrl extends MainCtrl{
	@Autowired
	private MemberTypeService memberTypeSrv;
	
	@GetMapping(value = "/list-membertypes")
	public ResponseEntity<?> listMembertypes(){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(memberTypeSrv.listMemberTypes(), HttpStatus.OK);
	}
	
	@GetMapping(value="/list-membertypes-by-isactive/{isActive}")
	public ResponseEntity<?> listMemberTypeByIsActive(@PathVariable(value = "isActive") Boolean isActive){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(memberTypeSrv.listMemberTypeByIsActive(isActive), HttpStatus.OK);
	}
	
	@PostMapping(value="/save-membertype")
	public ResponseEntity<?> saveMemberType(@RequestBody MemberType membertype){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		MemberType nMemberType = null;
		try {
			nMemberType = memberTypeSrv.saveMemberType(membertype);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return new ResponseEntity<>(nMemberType, HttpStatus.OK);
	}
	
	@PostMapping(value="/disable-membertype/{membertypeId}")
	public ResponseEntity<?> disableMemberType(@PathVariable(value = "membertypeId") Long memberTypeId){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		try {
			memberTypeSrv.disableMemberType(memberTypeId);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@GetMapping(value = "get-membertype-by-id/{idMemberType}")
	public ResponseEntity<?> getMemberTypeById(@PathVariable(value="idMemberType") Long idMemberType){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		MemberType memberTypeToLookFor = null;
		HttpStatus status = HttpStatus.OK;
		try {
			Optional<MemberType> memberTypeFound = memberTypeSrv.findMemberTypeById(idMemberType);
			if(memberTypeFound.isPresent()) {
				memberTypeToLookFor = memberTypeFound.get();
			}else {
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(memberTypeToLookFor, status);
	}
}

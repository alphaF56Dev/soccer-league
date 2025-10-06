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

import com.alexfierro.league.entity.Member;
import com.alexfierro.league.service.MemberService;

/**
 * @author alpha
 *
 */
@Controller
@RequestMapping(value="/member")
public class MemberCtrl extends MainCtrl{
	@Autowired
	private MemberService memberSrv;
	
	@GetMapping(value="/list-members")
	public ResponseEntity<?> listMembers(){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(memberSrv.listMembers(), HttpStatus.OK);
	}
	
	@GetMapping(value="/list-members-by-membertype/{membertype}")
	public ResponseEntity<?> listMembersByMemberType(@PathVariable(value="membertype") Long memberType){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}		
		return new ResponseEntity<>(memberSrv.listMemberByMemberTypeId(memberType), HttpStatus.OK);
	}
	
	@GetMapping(value="/list-members-by-isactive/{isActive}")
	public ResponseEntity<?> lsitMembersByIsActive(@PathVariable(value="isActive") Boolean isActive){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(memberSrv.listMemberByIsActive(isActive), HttpStatus.OK);
	}
	
	@PostMapping(value="/save-member")
	public ResponseEntity<?> saveMember(@RequestBody Member member){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		Member nMember = null;
		try {
			nMember = memberSrv.saveMember(member);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(nMember, HttpStatus.OK);
	}
	
	@PostMapping(value="/change-status")
	public ResponseEntity<?> changeStatus(@RequestBody Member member){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		HttpStatus status = HttpStatus.ACCEPTED;
		String msg = "";
		int code = -1;
		try {
			if(member.getIsActive()) {
				code = memberSrv.enableMember(member.getIdMember());
			}else {
				code = memberSrv.disableMember(member.getIdMember());
			}
			switch (code) {
			case 0: {
				status = HttpStatus.NOT_FOUND;
				msg = "Member to change status was not found.";
				break;
			}
			case 1:{
				msg = "Status was updated";
				status = HttpStatus.ACCEPTED;
				break;
			}
			default:
				msg = "Fail to update status.";
				status = HttpStatus.CONFLICT;
			}
		} catch (Exception e) {
			//return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			msg = e.getMessage();
		}
		return new ResponseEntity<>(msg, status);
	}
}

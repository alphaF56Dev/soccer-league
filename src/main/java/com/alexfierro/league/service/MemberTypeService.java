/**
 * 
 */
package com.alexfierro.league.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.entity.MemberType;
import com.alexfierro.league.repository.MemberTypeRepository;

/**
 * @author alpha
 *
 */
@Service
public class MemberTypeService {
	@Autowired
	private MemberTypeRepository memberTypeRep;
	
	public List<MemberType> listMemberTypes(){
		return memberTypeRep.findAll();
	}
	
	public MemberType saveMemberType(MemberType memberType) {
		return memberTypeRep.save(memberType);
	}
	
	/**
	 * This method disable a member type. Options to return:
	 * - 0: member type not identify
	 * - 1: member type disable correct
	 * @param memberTypeId
	 * @return
	 */
	public int disableMemberType(Long memberTypeId) {
		Optional<MemberType> memberType = memberTypeRep.findById(memberTypeId);
		int code_returned = 0;
		if(memberType.isPresent()) {
			MemberType memberTypeModif = memberType.get();
			memberTypeModif.setIsActive(false);
			code_returned = 1;
			memberTypeRep.save(memberTypeModif);
		}
		return code_returned;
	}
	
	/**
	 * Returns only records which are active or not, it depends that the user indicates.
	 * -true for active
	 * -false for not active
	 * @param isActive
	 * @return
	 */
	public List<MemberType> listMemberTypeByIsActive(Boolean isActive){
		return memberTypeRep.findByIsActive(isActive);
	}
}

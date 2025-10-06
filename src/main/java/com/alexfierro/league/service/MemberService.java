/**
 * 
 */
package com.alexfierro.league.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.entity.Member;
import com.alexfierro.league.repository.MemberRepository;

/**
 * @author alpha
 *
 */
@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRep;
	
	public List<Member> listMembers(){
		return memberRep.findAll();
	}
	
	public Member saveMember(Member member) {
		return memberRep.save(member);
	}
	
	/**
	 * Disable a member. Options to return:
	 * - 0: member not identify
	 * - 1: member is disabled
	 * @param memberId
	 * @return
	 */
	public int disableMember(Long memberId) {
		Optional<Member> memberModif = memberRep.findById(memberId);
		int code = 0;
		if (memberModif.isPresent()) {
			memberModif.get().setIsActive(false);
			memberRep.save(memberModif.get());
			code = 1;
		}
		return code;
	}
	
	/**
	 * Enable a member. Options to return:
	 * - 0: member not identify
	 * - 1: member is enabled
	 * @param memberId
	 * @return
	 */
	public int enableMember(Long memberId) {
		Optional<Member> memberModif = memberRep.findById(memberId);
		int code = 0;
		if (memberModif.isPresent()) {
			memberModif.get().setIsActive(true);
			memberRep.save(memberModif.get());
			code = 1;
		}
		return code;
	}
	
	public List<Member> listMemberByMemberTypeId(Long memberTypeId){
		return memberRep.findByMemberTypeId(memberTypeId);
	}
	
	public List<Member> listMemberByIsActive(Boolean isActive){
		return memberRep.findByIsActive(isActive);
	}
	
	public Optional<Member> findMemberById(Long memberId) {
		return memberRep.findById(memberId);
	}
}

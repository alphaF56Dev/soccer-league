/**
 * 
 */
package com.alexfierro.league.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.controller.LeagueException;
import com.alexfierro.league.entity.Member;
import com.alexfierro.league.entity.Referee;
import com.alexfierro.league.repository.RefereeRepository;

/**
 * @author alpha
 *
 */
@Service
public class RefereeService {
	
	@Autowired
	private RefereeRepository refereeRep;
	
	@Autowired
	private MemberService memberSrv;
	
	public List<Referee> listReferees() {
		return refereeRep.findAll();
	}
	
	/**
	 * Disable a referee.
	 * @param refereeId
	 * @return code to know if the action was successful 
	 * - 0: referee not found
	 * - 1: status updated.
	 */
	public int disableReferee(Long refereeId) {
		Optional<Referee> refereeModif = refereeRep.findById(refereeId);
		int code = 0;
		if(refereeModif.isPresent()) {
			code = 1;
			refereeModif.get().setIsActive(false);
			refereeRep.save(refereeModif.get());
		}
		return code;
	}
	
	/**
	 * Enable a referee.
	 * @param refereeId
	 * @return code to know if the action was successful 
	 * - 0: referee not found
	 * - 1: status updated.
	 */
	public int enableReferee(Long refereeId) {
		Optional<Referee> refereeModif = refereeRep.findById(refereeId);
		int code = 0;
		if(refereeModif.isPresent()) {
			code = 1;
			refereeModif.get().setIsActive(true);
			refereeRep.save(refereeModif.get());
		}
		return code;
	}
	
	public Referee saveReferee(Referee referee) throws LeagueException {
		int code = validateMemberType(referee.getMember().getIdMember());
		if (code != 1) {
			switch (code) {
			case 0:
				throw new LeagueException("Member was not found.");
			case 2:
				throw new LeagueException("Member type not valid to be a referee.");
			default:
				throw new LeagueException("Root cause was not identified to save data");
			}
		}
		Referee nReferee = refereeRep.save(referee);
		return nReferee;
	}
	
	/**
	 * This method validate the member type of the new referee to add. Only member with code "AR" can be referees.
	 * @param memberId
	 * @return 
	 * - 0: The member was not found.
	 * - 1: The member is valid.
	 * - 2: The member type is not valid to be a referee.
	 */
	private int validateMemberType(Long memberId) {
		int code = 0;
		Optional<Member> memberInfo = memberSrv.findMemberById(memberId);
		if(memberInfo.isPresent()) {
			if(memberInfo.get().getMemberType().getCode() == "AR") {
				code = 1;
			}else {
				code = 2;
			}
		}
		return code;
	}
}

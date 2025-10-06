/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.Member;

/**
 * @author alpha
 *
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	@Query("SELECT M FROM Member AS M WHERE M.memberType.idMember=:memberTypeId")
	public List<Member> findByMemberTypeId(Long memberTypeId);
	
	public List<Member> findByIsActive(Boolean isActive);
}

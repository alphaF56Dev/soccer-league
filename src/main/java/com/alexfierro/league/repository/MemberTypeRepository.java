/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.MemberType;

/**
 * @author alpha
 *
 */
@Repository
public interface MemberTypeRepository extends JpaRepository<MemberType, Long>{
	@Query("SELECT mt FROM MemberType AS mt WHERE isActive=:isActive")
	public List<MemberType> findByIsActive(Boolean isActive);
}

/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.Address;

/**
 * @author alpha
 *
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
	@Query("SELECT A FROM Address A WHERE A.idMember.idMember=?1 ORDER BY A.idAddress ASC")
	public List<Address> findAllByMemberId(Long idMember);
}

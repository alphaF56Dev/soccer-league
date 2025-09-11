/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.UserAccess;

/**
 * @author alpha
 *
 */
@Repository
public interface UserAccessRepository extends JpaRepository<UserAccess, Long>{
	public Optional<UserAccess> findByUsernameAndPassword(String username, String password);
}

/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.Municipality;

/**
 * @author alpha
 *
 */
@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long>{
	@Query("SELECT M FROM Municipality AS M WHERE M.idState.id_state=:id_state")
	public Optional<List<Municipality>> findByState(Long id_state);
}

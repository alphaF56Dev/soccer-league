/**
 * 
 */
package com.alexfierro.league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.Field;

/**
 * @author alpha
 *
 */
@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
	
}

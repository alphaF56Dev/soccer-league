/**
 * 
 */
package com.alexfierro.league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.PositionCatalog;

/**
 * @author alpha
 *
 */
@Repository
public interface PositionCatalogRepository extends JpaRepository<PositionCatalog, Long>{
}

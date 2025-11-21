/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.CategoryReferee;

/**
 * @author alpha
 *
 */
@Repository
public interface CategoryRefereeRepository extends JpaRepository<CategoryReferee, Long>{
	
	@Query("SELECT CR FROM CategoryReferee CR WHERE CR.idReferee.idReferee = ?1")
	public List<CategoryReferee> findByIdReferee(Long idReferee);
	
	@Query("SELECT CR FROM CategoryReferee CR WHERE CR.idCategoryLeague.idCategoryLeague = ?1")
	public List<CategoryReferee> findByIdCategoryLeague(Long idCategoryLeague);
	
	@Query("SELECT CR FROM CategoryReferee CR WHERE CR.idCategoryLeague.idCategoryLeague = ?1 AND CR.idReferee.idReferee = ?2")
	public CategoryReferee findByIdCategory_AND_IdReferee(Long idCategoryLeague, Long idReferee);
}

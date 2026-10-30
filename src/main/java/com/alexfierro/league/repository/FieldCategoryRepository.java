/**
 * 
 */
package com.alexfierro.league.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alexfierro.league.entity.FieldCategory;

/**
 * @author alpha
 *
 */
@Repository
public interface FieldCategoryRepository extends JpaRepository<FieldCategory, Long>{
	@Query("SELECT FC FROM FieldCategory AS FC WHERE FC.idCategoryLeague.idCategoryLeague = :idCategoryLeague")
	public List<FieldCategory> findFieldsByIdCategory(Long idCategoryLeague); 
	
	@Query("SELECT FC FROM FieldCategory AS FC WHERE FC.idField.idField = :idField")
	public List<FieldCategory> findCategoriesByIdField(Long idField);
	
	@Query("SELECT FC FROM FieldCategory AS FC WHERE FC.idField.idField = :idField AND FC.idCategoryLeague.idCategoryLeague = :idCategoryLeague")
	public FieldCategory findFieldByIdFieldAndIdCategory(Long idField,Long idCategoryLeague);
	
	@Query("SELECT FC FROM FieldCategory AS FC WHERE FC.idField.idField = :idField")
	public List<FieldCategory> findAllFieldCategoryByIdField(Long idField);
}

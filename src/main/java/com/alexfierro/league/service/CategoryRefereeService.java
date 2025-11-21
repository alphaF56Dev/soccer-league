/**
 * 
 */
package com.alexfierro.league.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.entity.CategoryLeague;
import com.alexfierro.league.entity.CategoryReferee;
import com.alexfierro.league.entity.Referee;
import com.alexfierro.league.repository.CategoryRefereeRepository;

/**
 * @author alpha
 *
 */
@Service
public class CategoryRefereeService {
	@Autowired
	private CategoryRefereeRepository catRefereeRep;
	
	public List<Referee> listRefereesPerCategory(Long idCategory) {
		List<CategoryReferee> categoryreferees = catRefereeRep.findByIdCategoryLeague(idCategory);
		List<Referee> referees = new ArrayList<>();
		if(categoryreferees.size() > 0) {
			for (int i = 0; i < categoryreferees.size(); i++) {
				referees.add(categoryreferees.get(i).getIdReferee());
			}
		}
		return referees;
	}
	
	public List<CategoryLeague> listCategoryPerReferees(Long idReferee) {
		List<CategoryReferee> categoryreferees = catRefereeRep.findByIdReferee(idReferee);
		List<CategoryLeague> categoryLeagues = new ArrayList<>();
		if(categoryreferees.size() > 0) {
			for (int i = 0; i < categoryreferees.size(); i++) {
				categoryLeagues.add(categoryreferees.get(i).getIdCategoryLeague());
			}
		}
		return categoryLeagues;
	}
	
	/**
	 * This method allows add new category to a Referee only if the category has not been added.
	 * @param idCategoryLeague
	 * @param idReferee
	 * @return code to know if what happen in the process:
	 * - 0: record is already exist.
	 * - 1: record added.
	 */
	public int addCategoryToReferee(Long idCategoryLeague, Long idReferee){
		int code = 0;
		CategoryReferee catReferee = catRefereeRep.findByIdCategory_AND_IdReferee(idCategoryLeague, idReferee);
		if (catReferee == null) {
			code = 1;
			CategoryReferee nCatReferee = new CategoryReferee();
			nCatReferee.setIdCategoryLeague(new CategoryLeague());
			nCatReferee.setIdReferee(new Referee());
			nCatReferee.getIdCategoryLeague().setIdCategoryLeague(idCategoryLeague);
			nCatReferee.getIdReferee().setIdReferee(idReferee);
			catRefereeRep.save(nCatReferee);
		}
		return code;
	}
	
	/**
	 * Delete a relation between category and referee.
	 * @param idCategoryLeague
	 * @param idReferee
	 * @return code to know what happen in the process
	 * - 0: relation not found.
	 * - 1: record deleted.
	 */
	public int removeCategoryOfReferee(Long idCategoryLeague, Long idReferee){
		int code = 0;
		CategoryReferee catReferee = catRefereeRep.findByIdCategory_AND_IdReferee(idCategoryLeague, idReferee);
		if(catReferee != null) {
			code = 1;
			catRefereeRep.delete(catReferee);
		}
		return code;
	}
}

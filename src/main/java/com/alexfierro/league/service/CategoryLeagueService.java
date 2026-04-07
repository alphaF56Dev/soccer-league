/**
 * 
 */
package com.alexfierro.league.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.entity.CategoryLeague;
import com.alexfierro.league.entity.Member;
import com.alexfierro.league.repository.CategoryLeagueRepository;

/**
 * @author alpha
 *
 */
@Service
public class CategoryLeagueService {
	@Autowired
	private CategoryLeagueRepository catLeagueRep;
	@Autowired
	private MemberService memberSrv;
	
	public List<CategoryLeague> listCategoryLeague(){		
		return catLeagueRep.findAll();
	}
	
	public CategoryLeague saveCategoryLeague(CategoryLeague categoryLeague) {
		return catLeagueRep.save(categoryLeague);
	}
	
	public List<CategoryLeague> findCategoryLeagueByName(String nameCategory) {		
		return findCategoryLeagueByName(nameCategory);
	}
	
	public CategoryLeague findCategoryById(Long categoryLeagueId) {
		CategoryLeague catLeagueFound = null;
		Optional<CategoryLeague> catLeague = catLeagueRep.findById(categoryLeagueId);
		if (catLeague.isPresent()) {
			catLeagueFound = (CategoryLeague)catLeague.get();
		}
		return catLeagueFound;
	}
	
	/**
	 * Disable a category league.
	 * @param categoryLeagueId
	 * @return code to know if the action was success
	 * 0: Category league was not found
	 * 1: Category league disabled
	 */
	public int disableCategoryLeague(Long categoryLeagueId) {
		int code = 0;
		Optional<CategoryLeague> catLeagueModif = catLeagueRep.findById(categoryLeagueId);
		if(catLeagueModif.isPresent()) {
			code = 1;
			catLeagueModif.get().setIsActive(false);
			catLeagueRep.save(catLeagueModif.get());
		}
		return code;
	}
	
	/**
	 * Enable a category league.
	 * @param categoryLeagueId
	 * @return code to know if the action was success
	 * 0: Category league was not found
	 * 1: Category league enabled
	 */
	public int enableCategoryLeague(Long categoryLeagueId) {
		int code = 0;
		Optional<CategoryLeague> catLeagueModif = catLeagueRep.findById(categoryLeagueId);
		if(catLeagueModif.isPresent()) {
			code = 1;
			catLeagueModif.get().setIsActive(true);
			catLeagueRep.save(catLeagueModif.get());
		}
		return code;
	}
	
	public List<CategoryLeague> getCategoriesLeagueByAge(Long idMember){
		Optional<Member> member = memberSrv.findMemberById(idMember);
		short currentAge = 0;
		if(member.isPresent()) {
			Period diff = Period.between(member.get().getBirthday().toLocalDate(), LocalDate.now());
			currentAge = (short) diff.getYears();
		}
		return catLeagueRep.findAllByAge(currentAge);
	}
}

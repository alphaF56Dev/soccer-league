/**
 * 
 */
package com.alexfierro.league.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.entity.FieldCategory;
import com.alexfierro.league.entity.dto.FieldCategoryDto;
import com.alexfierro.league.repository.FieldCategoryRepository;

/**
 * @author alpha
 *
 */
@Service
public class FieldCategoryService {
	@Autowired
	private FieldCategoryRepository fieldCatRep;
	
	public List<FieldCategoryDto> listAllFieldCategory(){
		return transformFieldCategoryListToDto(fieldCatRep.findAll());
	}
	
	public List<FieldCategoryDto> listFieldsByIdCategory(Long idCategory){
		return transformFieldCategoryListToDto(fieldCatRep.findFieldsByIdCategory(idCategory));
	}
	
	public List<FieldCategoryDto> listCategorieByIdField(Long idField){
		return transformFieldCategoryListToDto(fieldCatRep.findCategoriesByIdField(idField));
	}
	
	public Optional<FieldCategory> getFieldCategoryById(Long fieldCategryId){
		Optional <FieldCategory> fieldCatFound = fieldCatRep.findById(fieldCategryId);
		return fieldCatFound;
	}
	
	/**
	 * This method is responsible to save new Field Category record.
	 * @param fieldCategory Object with data to save in FieldCategory table 
	 * @return error code:
	 * 0: Already exist a record with the same fieldId and CategoryLeagueId
	 * 1: record saved successfully
	 */
	public int saveFieldCategory(FieldCategory fieldCategory) {
		int code = 1;
		if(fieldCategory.getIdFieldCategory() == null && validateFieldCategory(fieldCategory.getIdField().getIdField(), fieldCategory.getIdCategoryLeague().getIdCategoryLeague())) {
			code = 0;
		}else {
			fieldCatRep.save(fieldCategory);
		}
		return code;
	}
	
	public boolean validateFieldCategory(Long idField, Long idCategory) {
		FieldCategory fieldCatToLookFor = fieldCatRep.findFieldByIdFieldAndIdCategory(idField, idCategory);
		return fieldCatToLookFor != null;
	}
	
	/**
	 * This method transform FieldsCategory List in FieldCategoryDto List.
	 * @param fieldsCategory
	 * @return
	 */
	public List<FieldCategoryDto> transformFieldCategoryListToDto(List<FieldCategory> fieldsCategory){
		List<FieldCategoryDto> fieldsCategoryDto = new ArrayList<>();
		if (fieldsCategory != null && fieldsCategory.size() > 0) {
			for (FieldCategory fieldCategory : fieldsCategory) {
				fieldCategory.setMatchDays(convertMathDaysToStr(fieldCategory.getMatchDays()));
				fieldsCategoryDto.add(
						new FieldCategoryDto(fieldCategory)
						);
			}
		}
		return fieldsCategoryDto;
	}
	
	private String convertMathDaysToStr(String matchDays) {
		String newMatchDays = matchDays;
		if(matchDays.contains("1")) 
			newMatchDays = newMatchDays.replace("1", "Mon,");
		if(matchDays.contains("2")) 
			newMatchDays = newMatchDays.replace("2", "Tue,");
		if(matchDays.contains("3")) 
			newMatchDays = newMatchDays.replace("3", "Wed,");
		if(matchDays.contains("4")) 
			newMatchDays = newMatchDays.replace("4", "Thu,");
		if(matchDays.contains("5")) 
			newMatchDays = newMatchDays.replace("5", "Fri,");
		if(matchDays.contains("6")) 
			newMatchDays = newMatchDays.replace("6", "Sat,");
		if(matchDays.contains("7")) 
			newMatchDays = newMatchDays.replace("7", "Sun");
		return newMatchDays;
	}
}

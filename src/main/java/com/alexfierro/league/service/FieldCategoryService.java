/**
 * 
 */
package com.alexfierro.league.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.controller.LeagueException;
import com.alexfierro.league.entity.FieldCategory;
import com.alexfierro.league.entity.dto.FieldAvailabilityResultDto;
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
	 * This method allows to save new schedule for a field and category avoiding overlaps.
	 * @param fieldCategory: new schedule to save.
	 * @return result of the process. If the record is saved the FieldAvailabilityResultDto.statusCode must be 1.
	 */
	public FieldAvailabilityResultDto saveFieldCategory(FieldCategory fieldCategory) {
		List<String> fieldsOverSlap = new ArrayList<>();
		FieldAvailabilityResultDto fieldAvailabilityStatus = new FieldAvailabilityResultDto(1,"Field category saved successfully.");
		if(fieldCategory.getIdFieldCategory() == null && !validateHours(fieldCategory.getStartHour(), fieldCategory.getEndHour())) {
			fieldAvailabilityStatus.setCode(0);
			fieldAvailabilityStatus.setMessage("The start hour cannot be more than end hour");
		}else {
			fieldsOverSlap = validateFieldAvailability(fieldCategory.getStartHour(), fieldCategory.getEndHour(), 
					fieldCategory.getIdField().getIdField(), fieldCategory.getMatchDays());
			if(fieldsOverSlap.size() == 0) {
				fieldCatRep.save(fieldCategory);
			}else {
				fieldAvailabilityStatus.setCode(2);
				fieldAvailabilityStatus.setMessage("There is a overlap schedule. Please consider next overlaps: ");
				fieldAvailabilityStatus.setFieldsOverSlap(fieldsOverSlap);
			}				
		}
		return fieldAvailabilityStatus;
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
	
	private List<String> validateFieldAvailability(String startHour, String endHour, Long idField, String matchDays) {
		boolean isAvailable = true;
		List<FieldCategory> fieldsSchedule = fieldCatRep.findAllFieldCategoryByIdField(idField);
		List<String> fieldsOverSlap = new ArrayList<>();
		for (FieldCategory fieldCat : fieldsSchedule) {
			if (hasCommonDays(matchDays, fieldCat.getMatchDays())){
				if(hasHourOverlap(startHour, endHour, fieldCat.getStartHour(), fieldCat.getEndHour())) {
					fieldsOverSlap.add(fieldCat.getIdCategoryLeague().getName() + " - [" + fieldCat.getStartHour() + " - " + fieldCat.getEndHour() + "]");
				}
			}
		}
		if(fieldsOverSlap.size() > 0) 
			isAvailable = false;
		return fieldsOverSlap;
	}
	
	public boolean validateHours(String startHour, String endHour) {
		Integer startHourInt = (Integer.valueOf(startHour.substring(0, 2)));
		Integer endHourInt = (Integer.valueOf(endHour.substring(0, 2)));
		if(startHourInt > endHourInt) {
			return false;
		}
		return true;
	}
	
	
	private boolean hasCommonDays(String newDays, String existingDays) {
		boolean commonDays = false;
		for (int i = 0; i < existingDays.length(); i++) {
			String currentDay = existingDays.substring(i, i + 1);
			if(newDays.contains(currentDay)) {
				commonDays = true;
				break;
			}
		}
		return commonDays;
	}
	
	private boolean hasHourOverlap(
			String newStartHour,
			String newEndHour,
			String existingStartHour, 
			String existingEndHour) {
		boolean overlap = false;
		LocalTime nStartHour = LocalTime.parse(newStartHour);
		LocalTime nEndHour = LocalTime.parse(newEndHour);
		LocalTime eStartHour = LocalTime.parse(existingStartHour);
		LocalTime eEndHour = LocalTime.parse(existingEndHour);
		
		overlap = nStartHour.isBefore(eEndHour) && nEndHour.isAfter(eStartHour);
		
		return overlap;
	}
}

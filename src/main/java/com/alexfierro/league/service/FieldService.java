/**
 * 
 */
package com.alexfierro.league.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.entity.Field;
import com.alexfierro.league.entity.dto.FieldDto;
import com.alexfierro.league.repository.FieldRepository;

/**
 * @author alpha
 *
 */
@Service
public class FieldService {
	@Autowired
	private FieldRepository fieldRep;
	
	public List<FieldDto> listFields(){
		return transformFieldListToDtoList(fieldRep.findAll());
	}
	
	public Optional<Field> findFieldById(Long fieldId) {
		return fieldRep.findById(fieldId);
	}
	
	public FieldDto saveField(Field newField) {
		Field nField = fieldRep.save(newField);
		return convertFieldToDto(nField);
	}
	
	public FieldDto convertFieldToDto(Field field) {
		return new FieldDto(field);
	}
	
	public List<FieldDto> transformFieldListToDtoList(List<Field> fields){
		List<FieldDto> fieldsDto = new ArrayList<>();
		for (Field field : fields) {
			fieldsDto.add(new FieldDto(field));
		}
		return fieldsDto; 
	}
}

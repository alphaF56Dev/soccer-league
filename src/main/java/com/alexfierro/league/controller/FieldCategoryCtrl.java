/**
 * 
 */
package com.alexfierro.league.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexfierro.league.entity.FieldCategory;
import com.alexfierro.league.entity.dto.FieldAvailabilityResultDto;
import com.alexfierro.league.service.FieldCategoryService;

/**
 * @author alpha
 *
 */
@RestController
@RequestMapping(value = "/field-category")
public class FieldCategoryCtrl extends MainCtrl{
	@Autowired
	private FieldCategoryService fieldCatSrv;
	
	@GetMapping(value = "/list-all-fields-category")
	public ResponseEntity<?> listAllFieldsCategory(){
		if(!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(fieldCatSrv.listAllFieldCategory(), HttpStatus.OK); 
	}
	
	@GetMapping(value = "/list-fields-by-id-category/{idCategory}")
	public ResponseEntity<?> listFieldsByIdCategory(@PathVariable(value = "idCategory") Long idCategory){
		if(!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(fieldCatSrv.listFieldsByIdCategory(idCategory), HttpStatus.OK);
	}
	
	@GetMapping(value = "/list-categories-by-id-field/{idField}")
	public ResponseEntity<?> listCategoriesByIdField(@PathVariable(value="idField") Long idField){
		if(!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(fieldCatSrv.listCategorieByIdField(idField), HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-field-by-id/{idFieldCategory}")
	public ResponseEntity<?> getFieldById(@PathVariable(value = "idFieldCategory") Long idFieldCategory){
		if(!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(fieldCatSrv.getFieldCategoryById(idFieldCategory), HttpStatus.OK);
	}
	
	@PostMapping(value="/save-field-category")
	public ResponseEntity<?> saveFieldCategory(@RequestBody FieldCategory fieldCategory){
		if(!hasAccess()) {
			return new ResponseEntity<>("Not access to this point", HttpStatus.FORBIDDEN);
		}
		FieldAvailabilityResultDto validationResult = new FieldAvailabilityResultDto();
		HttpStatus status = HttpStatus.OK;
		
		try {
			if(fieldCategory.getIdFieldCategory() == null) {
				fieldCategory.setActive(true);
			}
			validationResult = fieldCatSrv.saveFieldCategory(fieldCategory);
			if(validationResult.getCode() != 1)
				status = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return new ResponseEntity<>(validationResult, status);
	}
}

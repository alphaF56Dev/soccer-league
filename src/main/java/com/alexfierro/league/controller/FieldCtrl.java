/**
 * 
 */
package com.alexfierro.league.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexfierro.league.entity.Field;
import com.alexfierro.league.entity.dto.FieldDto;
import com.alexfierro.league.service.FieldService;

/**
 * @author alpha
 *
 */
@RestController
@RequestMapping(value = "/field")
public class FieldCtrl extends MainCtrl{
	@Autowired
	private FieldService fieldSrv; 
	
	@GetMapping(value = "/list-fields")
	public ResponseEntity<?> listFields(){
		if (!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(fieldSrv.listFields(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-field-byId/{idField}")
	public ResponseEntity<?> getFieldById(@PathVariable(value = "idField") Long idField){
		if (!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		
		Field field = null;
		HttpStatus status = HttpStatus.OK;
		try {
			Optional<Field> fieldToLookFor = fieldSrv.findFieldById(idField);
			if(fieldToLookFor.isEmpty()) {
				status = HttpStatus.NOT_FOUND;
			}else {
				field = fieldToLookFor.get();
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(field, status);
	}
	
	@PostMapping(value = "/save-field")
	public ResponseEntity<?> saveField(@RequestBody Field newField){
		if (!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		FieldDto nField = null;
		try {
			nField = fieldSrv.saveField(newField);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(nField, HttpStatus.OK);
	}
}

/**
 * 
 */
package com.alexfierro.league.entity.dto;

import java.util.List;

/**
 * @author alpha
 *
 */
public class FieldAvailabilityResultDto extends StatusCodeResult{
	private List<String> fieldsOverSlap;
	
	public FieldAvailabilityResultDto() {
		super();
	}

	public FieldAvailabilityResultDto(Integer code, String message) {
		super(code, message);
	}

	public List<String> getFieldsOverSlap() {
		return fieldsOverSlap;
	}

	public void setFieldsOverSlap(List<String> fieldsOverSlap) {
		this.fieldsOverSlap = fieldsOverSlap;
	}
	
}

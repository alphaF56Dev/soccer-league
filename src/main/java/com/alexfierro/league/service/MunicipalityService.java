/**
 * 
 */
package com.alexfierro.league.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.entity.Municipality;
import com.alexfierro.league.entity.State;
import com.alexfierro.league.repository.MunicipalityRepository;

/**
 * @author alpha
 *
 */
@Service
public class MunicipalityService {
	@Autowired
	private MunicipalityRepository municipalityRep;
	
	@Autowired 
	private StateService stateSrv;
	
	public List<Municipality> listMunicipalities(){
		return municipalityRep.findAll();
	}
	
	public Municipality saveMunicipality(Municipality municipality){
		return municipalityRep.save(municipality);
	}
	
	public Optional<List<Municipality>> listMunicipalityByStateId(Long stateId){		
			return municipalityRep.findByState(stateId);
	}
}

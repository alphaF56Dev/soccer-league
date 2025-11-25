/**
 * 
 */
package com.alexfierro.league.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexfierro.league.entity.PositionCatalog;
import com.alexfierro.league.repository.PositionCatalogRepository;

/**
 * @author alpha
 *
 */
@Service
public class PositionCatalogService {
	@Autowired
	private PositionCatalogRepository posCatalogRep;
	
	public List<PositionCatalog> listPositions(){
		return posCatalogRep.findAll();
	}
	
	public PositionCatalog savePositionCatalog(PositionCatalog positionCatalog) {
		return posCatalogRep.save(positionCatalog);
	}
	
	public PositionCatalog getPositionById(Long posCatalogId) {
		PositionCatalog posCatalog = null;
		Optional<PositionCatalog> posCatalogFound = posCatalogRep.findById(posCatalogId);
		if (posCatalogFound.isPresent()) {
			posCatalog = posCatalogFound.get();
		}
		return posCatalog;
	}	
}

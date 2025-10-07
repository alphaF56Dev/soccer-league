/**
 * 
 */
package com.alexfierro.league.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexfierro.league.entity.Referee;
import com.alexfierro.league.service.RefereeService;

/**
 * @author alpha
 *
 */
@Controller
@RequestMapping(value="/referee")
public class RefereeCtrl extends MainCtrl{
	
	private static final Logger log = LoggerFactory.getLogger(RefereeCtrl.class);

	@Autowired
	private RefereeService refereeSrv;
	
	@GetMapping(value="/list-referees")
	public ResponseEntity<?> listReferee(){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(refereeSrv.listReferees(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/save-referee")
	public ResponseEntity<?> saveReferee(@RequestBody Referee referee){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		Referee nReferee = null ;
		try {
			nReferee = refereeSrv.saveReferee(referee);
		} catch (Exception e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(nReferee, HttpStatus.OK);
	}
	
	@PostMapping(value="/change-status")
	public ResponseEntity<?> changeStatus(@RequestBody Referee referee){
		if(!hasAccess()) {
			return new ResponseEntity<>("Access not allowed", HttpStatus.FORBIDDEN);
		}
		String msg = "";
		HttpStatus status = HttpStatus.OK;
		int code = -1;
		try {
			if (referee.getIsActive()) {
				code = refereeSrv.enableReferee(referee.getIdReferee());
			}else {
				code = refereeSrv.disableReferee(referee.getIdReferee());
			}
			switch (code) {
			case 0: {
				status = HttpStatus.NOT_FOUND;
				msg = "Member to change status was not found.";
				break;
			}
			case 1:{
				msg = "Status was updated";
				status = HttpStatus.ACCEPTED;
				break;
			}
			default:
				msg = "Fail to update status.";
				status = HttpStatus.CONFLICT;
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			msg = e.getMessage();
		}
		return new ResponseEntity<>(msg, status);
	}
}

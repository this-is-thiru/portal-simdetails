package com.infy.portal.simdetails.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.portal.simdetails.dto.SimDetailsDTO;
import com.infy.portal.simdetails.entity.SimDetails;
import com.infy.portal.simdetails.service.SimDetailsService;

@RestController
@RequestMapping("simdetails")
@CrossOrigin
public class SimDetailsController {
	@Autowired
	SimDetailsService simDetailsService;
	
	@PostMapping("insert")
	public ResponseEntity<String> addSimDetails(@Valid @RequestBody SimDetailsDTO simDetailsDTO){
	//		return new ResponseEntity<String>(sdd.toString(),HttpStatus.OK);
		return simDetailsService.addSimDetails(simDetailsDTO);
	}
	
	//Its a part of RestPointApi-5 and It is useful for SIM status change after the validation
	//http://localhost:9100/simdetails/1
	@GetMapping(value="fetchbyid/{simId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public SimDetails fetchById(@PathVariable("simId") int simId ){
		return simDetailsService.simStatusChange(simId).get();
	}
	
	@PostMapping("varification")
	public ResponseEntity<String> simDetailsVerification(@Valid @RequestBody SimDetailsDTO simDetailsDTO){
		return simDetailsService.simDetailsVerification(simDetailsDTO);
	}
	

}

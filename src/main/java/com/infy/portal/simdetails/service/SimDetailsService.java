package com.infy.portal.simdetails.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.infy.portal.simdetails.dto.SimDetailsDTO;
import com.infy.portal.simdetails.dto.SimOffersDTO;
import com.infy.portal.simdetails.entity.SimDetails;
import com.infy.portal.simdetails.repository.SimDetailsRepository;

@Service
public class SimDetailsService {
	
	@Autowired
	SimDetailsRepository simDetailsRepository;
	
	public ResponseEntity<String> addSimDetails(SimDetailsDTO simDetailsDTO){
		
		SimDetails simDetails=new SimDetails();
		simDetails.setSimId(simDetailsDTO.getSimId());
		simDetails.setServiceNumber(simDetailsDTO.getServiceNumber());
		simDetails.setSimNumber(simDetailsDTO.getSimNumber());
		simDetails.setSimStatus(simDetailsDTO.getSimStatus());
		
		simDetailsRepository.saveAndFlush(simDetails);
		return new ResponseEntity<String>(simDetails.toString(),HttpStatus.OK);
	}
	
	public Optional<SimDetails> simStatusChange(int simId){
		Optional<SimDetails> optionalSimDetails=simDetailsRepository.findById(simId);
		optionalSimDetails.get().setSimStatus("active");
		simDetailsRepository.saveAndFlush(optionalSimDetails.get());
		return optionalSimDetails;

	}
	
	public ResponseEntity<String> simDetailsVerification(SimDetailsDTO simDetailsDTO){
		String serviceNumber=simDetailsDTO.getServiceNumber();
		String simNumber=simDetailsDTO.getSimNumber();
		Optional<SimDetails> optionalSimDetails = simDetailsRepository.findByServiceNumberAndSimNumber(serviceNumber, simNumber);
		if(optionalSimDetails.isPresent()) {
			if(optionalSimDetails.get().getSimStatus().equals("active")==true) {
				return new ResponseEntity<String>("SIM already active",HttpStatus.OK);
			}
			else {
				optionalSimDetails.get().setSimStatus("active");
				Integer simId=optionalSimDetails.get().getSimId();
				simDetailsRepository.saveAndFlush(optionalSimDetails.get());
				
				SimOffersDTO simOffersDTO=new RestTemplate().getForObject("http://localhost:9500/simoffers/fetchoffer/"+simId, SimOffersDTO.class);
				return new ResponseEntity<String>("SIM activation successful"+"\n"+simOffersDTO.toString(),HttpStatus.OK);
			}
		}
		else {
			return new ResponseEntity<String>("Invalid details, please check again SIM number/Service number !",HttpStatus.BAD_REQUEST);
		}
		
		
	}

}

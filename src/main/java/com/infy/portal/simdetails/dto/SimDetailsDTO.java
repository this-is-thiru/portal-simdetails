package com.infy.portal.simdetails.dto;

import javax.validation.constraints.Pattern;

public class SimDetailsDTO {
	int simId;
	@Pattern(regexp="\\d{10}",message="Service Number must have 10 Digits")
	String serviceNumber;
	@Pattern(regexp="\\d{13}",message="Sim Number must have 13 Digits")
	String simNumber;
	String simStatus;
	
	public SimDetailsDTO(int simId, String serviceNumber,String simNumber,String simStatus) {
		super();
		this.simId = simId;
		this.serviceNumber = serviceNumber;
		this.simNumber = simNumber;
		this.simStatus = simStatus;
	}
	
	
	@Override
	public String toString() {
		return "SimDetailsDTO [simId=" + simId + ", serviceNumber=" + serviceNumber + ", simNumber=" + simNumber
				+ ", simStatus=" + simStatus + "]";
	}


	public int getSimId() {
		return simId;
	}
	public void setSimId(int simId) {
		this.simId = simId;
	}
	public String getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	public String getSimNumber() {
		return simNumber;
	}
	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}
	public String getSimStatus() {
		return simStatus;
	}
	public void setSimStatus(String simStatus) {
		this.simStatus = simStatus;
	}
	

}

package com.infy.portal.simdetails.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.portal.simdetails.entity.SimDetails;

public interface SimDetailsRepository extends JpaRepository<SimDetails,Integer> {
	Optional<SimDetails> findByServiceNumberAndSimNumber(String serviceNumber, String simNumber);

}

package com.topcoderrohit.monetization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.topcoderrohit.monetization.Service.DeveloperServices;
import com.topcoderrohit.monetization.model.Developer;
import com.topcoderrohit.monetization.model.DeveloperShareOutput;
import com.topcoderrohit.monetization.model.Transaction;

@RestController
public class DeveloperController {

	@Autowired
	DeveloperServices developerServicesImpl;

	/*
	 * This method will be used for Developer signup before using the monetization
	 * facility
	 */
	@PostMapping(value = "/api/developer/signup", produces = {})
	public int signUp(@RequestBody Developer developer) {
		int id = developerServicesImpl.signUp(developer);
		return id;
	}

	/*
	 * for every transaction update transaction table for month end developer
	 * calculations
	 */

	@PostMapping(value = "/api/developer/transaction", produces = {})
	public int addTransaction(@RequestBody Transaction tran) {
		int id = developerServicesImpl.addTransaction(tran);
		return id;
	}

	/*
	 * developer can check its total earning of the month
	 */
	@GetMapping("/api/developer/{developerId}")
	public List<DeveloperShareOutput> getDeveloperShare(@PathVariable(name = "developerId") int developerId) {
		List<DeveloperShareOutput> devShare = developerServicesImpl.getShare(developerId);
		return devShare;
	}

}
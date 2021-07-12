package com.topcoderrohit.monetization.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.topcoderrohit.monetization.DAO.DeveloperDAO;
import com.topcoderrohit.monetization.Service.DeveloperServices;
import com.topcoderrohit.monetization.Service.TransactionService;
import com.topcoderrohit.monetization.model.RevenueShare;
import com.topcoderrohit.monetization.model.ServiceRate;
import com.topcoderrohit.monetization.model.Services;

@RestController
public class TransactionController {

	@Autowired
	DeveloperDAO developerDAOImpl;
	@Autowired
	DeveloperServices developerServiceImpl;
	@Autowired
	TransactionService transactionServiceImpl;

	/*
	 * used by admin to add different api services and detail as-->created by
	 * developer, fixed share for developer,its freemium or not
	 */
	@PostMapping(value = "/api/service/add", produces = {})
	public int register(@RequestBody Services service) {
		int id = transactionServiceImpl.register(service);
		return id;
	}

	/*
	 * used by admin to add variable share plans for developers for different api
	 * services provided by them
	 */
	@PostMapping(value = "/api/revenue/add", produces = {})
	public List<Integer> revenue(@RequestBody List<RevenueShare> shares) {
		List<Integer> id = transactionServiceImpl.setRevenue(shares);
		return id;
	}

	/*
	 * used by admin to add rate for different api services and different plans
	 */
	@PostMapping(value = "/api/service/rate", produces = {})
	public int setRate(@RequestBody ServiceRate rate) {
		int id = transactionServiceImpl.setRate(rate);
		return id;
	}

	/*
	 * this method will register every requested api hit and check its allowed or
	 * not based on balance,plan end date,freemium facility provided, and in case of
	 * metered it will do transaction also so for metered transaction it will
	 * transfer the transaction amount to transaction table which will be used later
	 * to provide developer share and will print bill for the service used
	 */
	@GetMapping("/api/service/hit/{customerId}/{serviceId}")
	public boolean hit(@PathVariable(name = "customerId") int customerId,
			@PathVariable(name = "serviceId") int serviceId) {
		boolean allowHit = transactionServiceImpl.registerHit(customerId, serviceId);
		return allowHit;
	}

	/*
	 * this method is a scheduler which will run every year every month at day 1 time=00:05:00 
	 * this will calculate total share of developer and put 0 in transaction table
	 * after calculation for next month start
	 */
	@Scheduled(cron = "00 05 00 1 * *")
	@GetMapping(value = "/api/scheduler/share", produces = {})
	public Map<Integer, Double> startPaymentScheduler() {
		Map<Integer, Double> developerShareMap = developerServiceImpl.allDeveloperShare();
		System.out.println(developerServiceImpl.allDeveloperShare());
		return developerShareMap;

	}

	/*
	 * this method is a scheduler which will run at every night at 00:00:00 this will
	 * renew plans for type Daily-D,Monthly-M,Quarterly-Q,Yearly-Y and for all the
	 * renew plans it will transfer the transaction amount to transaction table
	 * which will be used later to provide developer share and will print bill for the service used
	 * 
	 */
	@Scheduled(cron = "00 00 00 * * *")
	@GetMapping(value = "/api/scheduler/renewal", produces = {})
	public void startRenewalScheduler() {
		transactionServiceImpl.renewPlan();

	}

}

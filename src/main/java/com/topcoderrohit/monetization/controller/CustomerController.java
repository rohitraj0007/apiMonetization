package com.topcoderrohit.monetization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.topcoderrohit.monetization.Service.CustomerServices;
import com.topcoderrohit.monetization.model.Customer;
import com.topcoderrohit.monetization.model.DeveloperShareOutput;
import com.topcoderrohit.monetization.model.SubscribeModel;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerServices customerServicesImpl;

	/*
	 * This method will be used for customer signup before using the monetization
	 * facility
	 */
	@PostMapping(value = "/api/customer/signup", produces = {})
	public int signUp(@RequestBody Customer customer) {
		int id = customerServicesImpl.signUp(customer);
		return id;
	}

	/*
	 * Customer request for to subscribe plan will be served by this method
	 */
	@PostMapping("/api/customer/subscribe")
	public int subscribe(@RequestBody SubscribeModel model) throws Exception {
		int id = customerServicesImpl.subscribe(model);
		return id;

	}
	/*
	 * Customer request for subscribed plan 
	 * will print the bill also
	 */
	@GetMapping("/api/customer/cancel/{customerId}/{serviceId}")
	public void cancelSubscription(@PathVariable(name = "customerId") int customerId,@PathVariable(name = "serviceId") int serviceId) {
		customerServicesImpl.cancelSubscription(customerId,serviceId);
	}
}

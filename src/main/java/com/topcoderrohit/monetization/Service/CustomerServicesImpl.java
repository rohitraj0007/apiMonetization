package com.topcoderrohit.monetization.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcoderrohit.monetization.DAO.CustomerDAO;
import com.topcoderrohit.monetization.DAO.TransactionDAO;
import com.topcoderrohit.monetization.model.Customer;
import com.topcoderrohit.monetization.model.CustomerService;
import com.topcoderrohit.monetization.model.Freemium;
import com.topcoderrohit.monetization.model.ServiceRate;
import com.topcoderrohit.monetization.model.SubscribeModel;
import com.topcoderrohit.monetization.model.Transaction;
import com.topcoderrohit.monetization.util.PlanConstants;

@Service

public class CustomerServicesImpl implements CustomerServices {
	
	@Autowired
	CustomerDAO customerDAOImpl;

	@Autowired
	TransactionDAO transactionDAOImpl;

	@Override
	public int signUp(Customer customer) {
		int id = customerDAOImpl.signUp(customer);
		return id;
	}

	@Override
	public int subscribe(SubscribeModel model) throws Exception {
		// TODO Auto-generated method stub
		LocalDateTime date = LocalDateTime.now();
		if (model.getFreemiumType().equals('N')) {
			// no freemium plan, so go for direct paid plans
			// implement rule 1
			CustomerService service = subscribeToPaidPlan(model);
			int customerServiceId = customerDAOImpl.savePaidAccount(service);
			// now add row in transaction table
			Transaction transaction = getTransactionForPlan(service);
			customerDAOImpl.saveTransaction(transaction);
		}
		// freemium plan, so will wait till the freemium will exhaust for paid plan
		// implement rule 2
		else {
			Freemium free = new Freemium();
			free.setActive(true);
			free.setConsumerId(model.getCustomerId());
			free.setServiceId(model.getServiceId());
			free.setType(model.getFreemiumType());
			if (model.getFreemiumType() == 'D') {
				free.setDuration(date.plusDays(Long.parseLong(model.getDuration())));
			} else if (model.getFreemiumType() == 'V') {
				free.setVolume(model.getVolume());
			} else {
				free.setVolume(model.getVolume());
				free.setDuration(date.plusDays(Long.parseLong(model.getDuration())));
			}

			free.setTypePostFreemium(model.getPlanType());
			// now set data in freemium
			customerDAOImpl.saveFreemiumAccount(free);
			// now set data in consumerservice to use post premium
			CustomerService service = new CustomerService();
			service.setFreemiumActive(true);
			service.setActive(true);
			service.setPlanType(model.getPlanType());
			service.setCustomerId(model.getCustomerId());
			service.setServiceId(model.getServiceId());
			customerDAOImpl.savePaidAccount(service);
			// other value like volume and end date will be set when transaction will be
			// triggered post freemium

		}
		return 0;
	}

	private Transaction getTransactionForPlan(CustomerService service)throws Exception {
		// TODO Auto-generated method stub
		Transaction tranPaid = new Transaction();
		tranPaid.setDeveloperServiceId(service.getServiceId());
		if (service.getPlanType() == 'U')
			tranPaid.setTransactionTotal(0);
		else
			tranPaid.setTransactionTotal(getTotalTranPaid(service));

		return tranPaid;
	}

	private double getTotalTranPaid(CustomerService service) {
		// TODO Auto-generated method stub
		
		ServiceRate rate = transactionDAOImpl.getRateChart(service.getServiceId());
		double cost = service.getPlanType() == 'U' ? rate.getMeterRate()
				: service.getPlanType() == 'D' ? rate.getRateDaily()
						: service.getPlanType() == 'M' ? rate.getRateMonthly()
								: service.getPlanType() == 'Q' ? rate.getRateQuaterly() : rate.getRateYearly();
		System.out.println("Customer is getting charged for service as :"+service.toString());
		System.out.println("Charges are :"+cost);

		return cost;
	}

	private CustomerService subscribeToPaidPlan(SubscribeModel model) {
		// TODO Auto-generated method stub
		LocalDateTime date = LocalDateTime.now();
		LocalDateTime endOfTodayDate = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 23, 59);
		// endOfTodayDate.
		CustomerService service = new CustomerService();
		service.setFreemiumActive(false);
		service.setCustomerId(model.getCustomerId());
		service.setServiceId(model.getServiceId());
		// service.setFreemiumType(model.getFreemiumType());
		service.setActive(true);
		service.setPlanType(model.getPlanType());
		switch (model.getPlanType()) {
		case 'U':
			System.out.println("Metered/unit based plan");
			break;
		case 'D':
			System.out.println("Daily plan");
			service.setPlanEndVolume(PlanConstants.PLAN_DAILY_LIMIT);
			service.setPlanEndDate(endOfTodayDate);
			break;
		case 'M':
			System.out.println("Monthly plan");
			service.setPlanEndVolume(PlanConstants.PLAN_MONTHLY_LIMIT);
			service.setPlanEndDate(date.plusDays(30));
			break;
		case 'Q':
			System.out.println("Quaterly plan");
			service.setPlanEndVolume(PlanConstants.PLAN_QUATERLY_LIMIT);
			service.setPlanEndDate(date.plusDays(90));
			break;
		case 'Y':
			System.out.println("Yearly plan");
			service.setPlanEndVolume(PlanConstants.PLAN_YEARLY_LIMIT);
			service.setPlanEndDate(date.plusDays(365));
			break;

		}
		return service;
	}

	@Override
	public void cancelSubscription(int customerId, int serviceId) {
		// TODO Auto-generated method stub
		customerDAOImpl.cancelPlan(customerId,serviceId);
		
	}

}

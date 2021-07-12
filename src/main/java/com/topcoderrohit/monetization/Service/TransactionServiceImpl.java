package com.topcoderrohit.monetization.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcoderrohit.monetization.DAO.CustomerDAO;
import com.topcoderrohit.monetization.DAO.TransactionDAO;
import com.topcoderrohit.monetization.model.CustomerService;
import com.topcoderrohit.monetization.model.Freemium;
import com.topcoderrohit.monetization.model.RevenueShare;
import com.topcoderrohit.monetization.model.ServiceRate;
import com.topcoderrohit.monetization.model.Services;
import com.topcoderrohit.monetization.model.Transaction;
import com.topcoderrohit.monetization.util.PlanConstants;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	TransactionDAO transactionDAOImpl;
	@Autowired
	CustomerDAO customerDAOImpl;

	@Override
	public int register(Services services) {
		int id = transactionDAOImpl.register(services);
		return id;
	}

	@Override
	public List<Integer> setRevenue(List<RevenueShare> shares) {
		List<Integer> id = transactionDAOImpl.setRevenue(shares);
		return id;

	}

	@Override
	public int setRate(ServiceRate rate) {
		// TODO Auto-generated method stub
		int id = transactionDAOImpl.setRate(rate);
		return id;
	}

	@Override
	public boolean registerHit(int customerId, int serviceId) {
		// TODO Auto-generated method stub
		boolean response=false;
		CustomerService serviceDetail = transactionDAOImpl.getCustomerServiceDetail(customerId, serviceId);
		if (serviceDetail.isActive()) {
			if (serviceDetail.isFreemiumActive()) {
				Freemium free = new Freemium();
				free = transactionDAOImpl.getFreemiumDetail(customerId, serviceId);
				if (free.isActive()) {
					char type = free.getType();
					switch (type) {
					case 'D':
						System.out.println("Duration check");
						if (!free.getDuration().isBefore(LocalDateTime.now())) {
							// decrease the volume;
							transactionDAOImpl.setFreemiumLimit(free);
							response= true;
						} else {
							response= false;
						}
						break;
					case 'V':
						System.out.println("Volume check");
						if (!(free.getVolume() == 0)) {
							transactionDAOImpl.setFreemiumLimit(free);
							response= true;
							// decrease the volume;
						} else {
							response= false;
						}

						break;
					case 'B':
						System.out.println("Duration and Volume check");
						if (!free.getDuration().isBefore(LocalDateTime.now()) && !(free.getVolume() == 0)) {
							transactionDAOImpl.setCustomerServiceLimit(serviceDetail);
							response= true;
							// decrease the volume;
						} else {
							response= false;
						}
						

						break;
					}

				}
			} else {
				if(serviceDetail.getPlanType()=='U') {
					//customer will be charged and developer will get its share
					Transaction transaction = customerDAOImpl.getTransaction(serviceDetail.getServiceId());
					transaction.setTransactionTotal(getTotalTranPaid(serviceDetail)+transaction.getTransactionTotal());
					transaction.setDeveloperServiceId(serviceDetail.getServiceId());
					customerDAOImpl.saveUpdateTransaction(transaction);//transaction table is updated
				}
				
				if (serviceDetail.getPlanEndDate().isBefore(LocalDateTime.now())) {
					response= false;
				} else if (serviceDetail.getPlanEndVolume() == 0) {
					response= false;
				} else {
					transactionDAOImpl.setCustomerServiceLimit(serviceDetail);
					response= true;
				}
			}
		} else {
			System.out.println("Not Active");
			response= false;
		}
		return response;
	}

	@Override
	public void renewPlan() {
		// TODO Auto-generated method stub
		List<CustomerService> renewCustomersWithOldPlan=transactionDAOImpl.getCustomersWithoutFreemiumByEndDate();
		List<CustomerService> renewCustomersWithFreemium=transactionDAOImpl.getCustomersWithFreemiumEnd();
		List<CustomerService> renewCustomersWithPlan=new ArrayList<CustomerService>();
		renewCustomersWithPlan.addAll(renewCustomersWithOldPlan);
		renewCustomersWithOldPlan.addAll(renewCustomersWithFreemium);
		List<CustomerService> renewCustomersWithNewPlan=new ArrayList<CustomerService>();
		renewCustomersWithNewPlan=subscribeToPaidPlan(renewCustomersWithOldPlan);
		for(CustomerService service:renewCustomersWithNewPlan) {
			customerDAOImpl.saveUpdatePaidAccount(service);
		Transaction transaction = customerDAOImpl.getTransaction(service.getServiceId());
		transaction=getTransactionForPlan(transaction,service);
		customerDAOImpl.saveUpdateTransaction(transaction);
		}
	}

	private Transaction getTransactionForPlan(Transaction tran,CustomerService service) {
		// TODO Auto-generated method stub

		if (service.getPlanType() == 'U')
			tran.setTransactionTotal(0);
		else
			tran.setTransactionTotal(tran.getTransactionTotal()+getTotalTranPaid(service));

		return tran;
	}

	private double getTotalTranPaid(CustomerService service) {
		// TODO Auto-generated method stub
		ServiceRate rate = transactionDAOImpl.getRateChart(service.getServiceId());
		double cost = service.getPlanType() == 'U' ? rate.getMeterRate()
				: service.getPlanType() == 'D' ? rate.getRateDaily()
						: service.getPlanType() == 'M' ? rate.getRateMonthly()
								: service.getPlanType() == 'Q' ? rate.getRateQuaterly() : rate.getRateYearly();

		return cost;
	}

	private List<CustomerService>  subscribeToPaidPlan(List<CustomerService> renewCustomersWithOldPlan) {
			// TODO Auto-generated method stub
		List<CustomerService> renewCustomersWithNewPlan=new ArrayList<CustomerService>();
			LocalDateTime date = LocalDateTime.now();
			LocalDateTime endOfTodayDate = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 23, 59);
			// endOfTodayDate.
			for(CustomerService service:renewCustomersWithOldPlan) {
			service.setFreemiumActive(false);
			service.setActive(true);
			switch (service.getPlanType()) {
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
			renewCustomersWithNewPlan.add(service);
			}
			return renewCustomersWithNewPlan;
		}


}

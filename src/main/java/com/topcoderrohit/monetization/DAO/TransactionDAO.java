package com.topcoderrohit.monetization.DAO;

import java.util.List;

import com.topcoderrohit.monetization.model.CustomerService;
import com.topcoderrohit.monetization.model.Freemium;
import com.topcoderrohit.monetization.model.RevenueShare;
import com.topcoderrohit.monetization.model.ServiceRate;
import com.topcoderrohit.monetization.model.Services;

public interface TransactionDAO {

	public int register(Services services);

	public List<Integer> setRevenue(List<RevenueShare> shares);

	public int setRate(ServiceRate rate);

	public ServiceRate getRateChart(int serviceId);

	public CustomerService getCustomerServiceDetail(int customerId, int serviceId);

	public Freemium getFreemiumDetail(int customerId, int serviceId);

	public void setFreemiumLimit(Freemium free);

	public void setCustomerServiceLimit(CustomerService service);

	public List<CustomerService> getCustomersWithoutFreemiumByEndDate();

	public List<CustomerService> getCustomersWithFreemiumEnd();

}

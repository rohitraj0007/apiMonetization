package com.topcoderrohit.monetization.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.topcoderrohit.monetization.model.RevenueShare;
import com.topcoderrohit.monetization.model.ServiceRate;
import com.topcoderrohit.monetization.model.Services;
@Transactional
public interface TransactionService {

	public int register(Services service);

	public List<Integer> setRevenue(List<RevenueShare> shares);

	public int setRate(ServiceRate rate);

	public boolean registerHit(int customerId, int serviceId);

	public void renewPlan();

}

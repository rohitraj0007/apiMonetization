package com.topcoderrohit.monetization.DAO;

import com.topcoderrohit.monetization.model.Customer;
import com.topcoderrohit.monetization.model.CustomerService;
import com.topcoderrohit.monetization.model.Freemium;
import com.topcoderrohit.monetization.model.Transaction;

public interface CustomerDAO {

	public int signUp(Customer customer);

	public int saveFreemiumAccount(Freemium free);

	public int savePaidAccount(CustomerService service);

	public int saveTransaction(Transaction transaction);

	void saveUpdatePaidAccount(CustomerService service);

	public Transaction getTransaction(int serviceId);

	void saveUpdateTransaction(Transaction transaction);

	public void cancelPlan(int customerId, int serviceId);

}

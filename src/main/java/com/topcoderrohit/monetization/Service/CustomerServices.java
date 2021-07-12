package com.topcoderrohit.monetization.Service;

import org.springframework.transaction.annotation.Transactional;
import com.topcoderrohit.monetization.model.Customer;
import com.topcoderrohit.monetization.model.SubscribeModel;

@Transactional
public interface CustomerServices {

	public int signUp(Customer customer);

	public int subscribe(SubscribeModel model) throws Exception;

	public void cancelSubscription(int customerId, int serviceId);

}

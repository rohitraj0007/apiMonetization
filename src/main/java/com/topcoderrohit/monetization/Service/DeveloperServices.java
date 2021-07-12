package com.topcoderrohit.monetization.Service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.topcoderrohit.monetization.model.Developer;
import com.topcoderrohit.monetization.model.DeveloperShareOutput;
import com.topcoderrohit.monetization.model.Transaction;

@Transactional
public interface DeveloperServices {

	public int signUp(Developer developer);

	// public int addService(DeveloperService devService);

	public int addTransaction(Transaction tran);

	public List<DeveloperShareOutput> getShare(int developerId);
	
	public Map<Integer, Double> allDeveloperShare();

}

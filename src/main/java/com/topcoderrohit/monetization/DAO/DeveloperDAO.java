package com.topcoderrohit.monetization.DAO;

import java.util.List;

import com.topcoderrohit.monetization.model.Developer;
import com.topcoderrohit.monetization.model.DeveloperShareOutput;
import com.topcoderrohit.monetization.model.DeveloperTotalShare;
import com.topcoderrohit.monetization.model.Transaction;

public interface DeveloperDAO {

	public int signUp(Developer developer);

	public int addTransaction(Transaction tran);

	public List<DeveloperShareOutput> getFixedShare(int developerId);

	public List<DeveloperShareOutput> getFlexibleShare(int developerId);

	public List<DeveloperTotalShare> getFixedShare();

	public List<DeveloperTotalShare> getFlexibleShare();

	public void rebootShareInTransaction();
}

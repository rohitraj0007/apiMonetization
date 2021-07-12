package com.topcoderrohit.monetization.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topcoderrohit.monetization.DAO.DeveloperDAO;
import com.topcoderrohit.monetization.model.Developer;
import com.topcoderrohit.monetization.model.DeveloperShareOutput;
import com.topcoderrohit.monetization.model.DeveloperTotalShare;
import com.topcoderrohit.monetization.model.Transaction;

@Service
public class DeveloperServicesImpl implements DeveloperServices {

	@Autowired
	DeveloperDAO developerDAOImpl;

	@Override
	public int signUp(Developer developer) {
		// TODO Auto-generated method stub
		int id = developerDAOImpl.signUp(developer);
		return id;

	}

	/*
	 * @Override public int addService(DeveloperService devService) { // TODO
	 * Auto-generated method stub int id=developerDAOImpl.addService(devService);
	 * return id; }
	 */
	@Override
	public int addTransaction(Transaction tran) {

		int id = developerDAOImpl.addTransaction(tran);
		return id;
	}

	@Override
	public List<DeveloperShareOutput> getShare(int developerId) {
		List<DeveloperShareOutput> fixedShare = new ArrayList<DeveloperShareOutput>();
		List<DeveloperShareOutput> flexibleShare = new ArrayList<DeveloperShareOutput>();
		List<DeveloperShareOutput> share = new ArrayList<DeveloperShareOutput>();
		fixedShare = developerDAOImpl.getFixedShare(developerId);
		flexibleShare = developerDAOImpl.getFlexibleShare(developerId);
		share.addAll(fixedShare);
		share.addAll(flexibleShare);

		return share;
	}
	
	@Override
	public Map<Integer, Double> allDeveloperShare() {
		List<DeveloperTotalShare> fixedShare = new ArrayList<DeveloperTotalShare>();
		List<DeveloperTotalShare> flexibleShare = new ArrayList<DeveloperTotalShare>();
		List<DeveloperTotalShare> share = new ArrayList<DeveloperTotalShare>();
		fixedShare = developerDAOImpl.getFixedShare();
		flexibleShare = developerDAOImpl.getFlexibleShare();
		developerDAOImpl.rebootShareInTransaction();
		Map<Integer, Double> developerShareMap = totalShareIdMap(fixedShare, flexibleShare);
		return developerShareMap;
	}

	private Map<Integer, Double> totalShareIdMap(List<DeveloperTotalShare> fixedShare,
			List<DeveloperTotalShare> flexibleShare) {
		Map<Integer, Double> developerShareMap = new HashMap<Integer, Double>();
		for (DeveloperTotalShare share : fixedShare) {
			developerShareMap.put(Integer.valueOf(share.getDeveloperId()), share.getAmount());
		}
		for (DeveloperTotalShare share : flexibleShare) {
			int id = Integer.valueOf(share.getDeveloperId());
			if (developerShareMap.containsKey(id))
				developerShareMap.put(id, developerShareMap.get(id) + share.getAmount());
			else
				developerShareMap.put(id, share.getAmount());
		}
		return developerShareMap;
	}

}

package com.topcoderrohit.monetization.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.topcoderrohit.monetization.model.Developer;
import com.topcoderrohit.monetization.model.DeveloperShareOutput;
import com.topcoderrohit.monetization.model.DeveloperTotalShare;
import com.topcoderrohit.monetization.model.Transaction;
import com.topcoderrohit.monetization.util.HibernateUtil;

@Repository
public class DeveloperDAOImpl implements DeveloperDAO {

	@Override
	public int signUp(Developer developer) {
		// TODO Auto-generated method stub
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		int id = (int) sessionOne.save(developer);
		sessionOne.getTransaction().commit();
		return id;
	}

	/*
	 * @Override public int addService(DeveloperService devService) { // TODO
	 * Auto-generated method stub Session sessionOne =
	 * HibernateUtil.getSessionFactory().openSession();
	 * sessionOne.beginTransaction(); int id = (int) sessionOne.save(devService);
	 * sessionOne.getTransaction().commit(); return id; }
	 */

//add or update the service related total transaction based on developerServiceId
	@Override
	public int addTransaction(Transaction tranNew) {
		// TODO Auto-generated method stub
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		Criteria cr = sessionOne.createCriteria(Transaction.class);
		cr.add(Restrictions.eq("developerServiceId", tranNew.getDeveloperServiceId()));
		List results = cr.list();
		int id;
		if (results.size() == 1) {
			Transaction tranOld = (Transaction) results.get(0);
			tranNew.setTransactionTotal(tranOld.getTransactionTotal() + tranNew.getTransactionTotal());
			tranNew.setId(tranOld.getId());
			sessionOne.update(tranNew);
			id = tranOld.getId();
		} else {
			id = (int) sessionOne.save(tranNew);
		}
		sessionOne.getTransaction().commit();
		return id;
	}

	@Override
	public List<DeveloperShareOutput> getFixedShare(int developerId) {
		// TODO Auto-generated method stub
		List<DeveloperShareOutput> fixedShare = new ArrayList<DeveloperShareOutput>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "select s.id,s.charge,t.transactionTotal from Services s,Transaction t"
				+ " where s.id=t.developerServiceId and s.fixedCharge=true and s.developerId=:id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", developerId);
		List<Object[]> list = query.getResultList();// int,double,double
		// prepare DeveloperShareOutput
		DeveloperShareOutput output;
		for (Object[] obj : list) {
			output = new DeveloperShareOutput();
			output.setDeveloperId(String.valueOf(developerId));
			output.setServiceId(String.valueOf(obj[0]));
			output.setAmount(calculateShare(obj[1], obj[2]));
			fixedShare.add(output);
		}
		return fixedShare;
	}

	private double calculateShare(Object charge, Object transactionTotal) {
		String str = charge.toString();
		double c = Double.valueOf(str).doubleValue();
		String str1 = transactionTotal.toString();
		double t = Double.valueOf(str1).doubleValue();
		double share = c * (0.01) * t;
		return share;
	}

	@Override
	public List<DeveloperShareOutput> getFlexibleShare(int developerId) {
		List<DeveloperShareOutput> flexibleShare = new ArrayList<DeveloperShareOutput>();
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "select s.id, t.transactionTotal,r.percentageVariableCharge from Services s,Transaction t,RevenueShare r"
				+ " where s.developerId=:id and s.id=r.developerServiceId and s.id=t.developerServiceId and s.fixedCharge=false and t.transactionTotal between r.lowerLimit and r.upperLimit";
		Query query = session.createQuery(hql);
		query.setParameter("id", developerId);
		List<Object[]> list = query.getResultList();// int,double,double
		// prepare DeveloperShareOutput
		DeveloperShareOutput output;
		for (Object[] obj : list) {
			output = new DeveloperShareOutput();
			output.setDeveloperId(String.valueOf(developerId));
			output.setServiceId(String.valueOf(obj[0]));
			output.setAmount(calculateShare(obj[1], obj[2]));
			flexibleShare.add(output);
		}
		return flexibleShare;

	}

	@Override
	public List<DeveloperTotalShare> getFixedShare() {
		List<DeveloperTotalShare> fixedShare = new ArrayList<DeveloperTotalShare>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "select s.developerId,SUM(s.charge*t.transactionTotal*.01) from Services s,Transaction t"
				+ " where s.id=t.developerServiceId and s.fixedCharge=true group by s.developerId ";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.getResultList();// int,double,double
		// prepare DeveloperShareOutput
		DeveloperTotalShare output;
		for (Object[] obj : list) {
			String str = obj[1].toString();
			double c = Double.valueOf(str).doubleValue();
			output = new DeveloperTotalShare();
			output.setDeveloperId(String.valueOf(obj[0]));
			output.setAmount(c);
			fixedShare.add(output);
		}
		return fixedShare;
	}

	@Override
	public List<DeveloperTotalShare> getFlexibleShare() {
		List<DeveloperTotalShare> flexibleShare = new ArrayList<DeveloperTotalShare>();
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "select s.developerId,SUM( t.transactionTotal*r.percentageVariableCharge*.01) from Services s,Transaction t,RevenueShare r"
				+ " where s.id=r.developerServiceId and s.id=t.developerServiceId and s.fixedCharge=false and t.transactionTotal between r.lowerLimit and r.upperLimit group by s.developerId ";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.getResultList();// int,double,double
		// prepare DeveloperShareOutput
		DeveloperTotalShare output;
		for (Object[] obj : list) {
			String str = obj[1].toString();
			double c = Double.valueOf(str).doubleValue();
			output = new DeveloperTotalShare();
			output.setDeveloperId(String.valueOf(obj[0]));
			output.setAmount(c);
			flexibleShare.add(output);
		}
		return flexibleShare;

	}

	@Override
	public void rebootShareInTransaction() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "update Transaction t set t.transactionTotal =0 where 1=1";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		session.getTransaction().commit();

	}

}

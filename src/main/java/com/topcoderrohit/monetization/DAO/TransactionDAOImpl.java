package com.topcoderrohit.monetization.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import com.topcoderrohit.monetization.model.CustomerService;
import com.topcoderrohit.monetization.model.Freemium;
import com.topcoderrohit.monetization.model.RevenueShare;
import com.topcoderrohit.monetization.model.ServiceRate;
import com.topcoderrohit.monetization.model.Services;
import com.topcoderrohit.monetization.util.HibernateUtil;

@Repository
public class TransactionDAOImpl implements TransactionDAO {

	@Override
	public int register(Services services) {
		// TODO Auto-generated method stub
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		int id = (int) sessionOne.save(services);
		sessionOne.getTransaction().commit();
		return id;

	}

	@Override
	public List<Integer> setRevenue(List<RevenueShare> shares) {
		// TODO Auto-generated method stub
		List<Integer> bracketList = new ArrayList<Integer>();
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		for (RevenueShare share : shares) {
			int id = (int) sessionOne.save(share);
			bracketList.add(id);
		}

		sessionOne.getTransaction().commit();

		return bracketList;
	}

	@Override
	public int setRate(ServiceRate rate) {
		// TODO Auto-generated method stub
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		int id = (int) sessionOne.save(rate);
		sessionOne.getTransaction().commit();

		return id;
	}

	@Override
	public ServiceRate getRateChart(int serviceId) {
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		Criteria cr = sessionOne.createCriteria(ServiceRate.class);
		cr.add(Restrictions.eq("serviceId", String.valueOf(serviceId)));
		List results = cr.list();
		ServiceRate rate = (ServiceRate) results.get(0);
		sessionOne.getTransaction().commit();

		return rate;

	}

	@Override
	public CustomerService getCustomerServiceDetail(int customerId, int serviceId) {
		// TODO Auto-generated method stub
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		Criteria cr = sessionOne.createCriteria(CustomerService.class);
		cr.add(Restrictions.eq("serviceId", String.valueOf(serviceId)));
		cr.add(Restrictions.eq("customerId", String.valueOf(customerId)));
		List results = cr.list();
		CustomerService serviceDetail = (CustomerService) results.get(0);
		sessionOne.getTransaction().commit();
		return serviceDetail;
	}

	@Override
	public Freemium getFreemiumDetail(int customerId, int serviceId) {
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		Criteria cr = sessionOne.createCriteria(Freemium.class);
		cr.add(Restrictions.eq("serviceId", String.valueOf(serviceId)));
		cr.add(Restrictions.eq("customerId", String.valueOf(customerId)));
		List results = cr.list();
		Freemium free = (Freemium) results.get(0);
		sessionOne.getTransaction().commit();
		return free;
	}

	@Override
	public void setFreemiumLimit(Freemium free) {
		// TODO Auto-generated method stub
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		sessionOne.beginTransaction();
		free.setVolume(free.getVolume() - 1);
		sessionOne.saveOrUpdate(free);
		sessionOne.getTransaction().commit();

	}

	@Override
	public void setCustomerServiceLimit(CustomerService service) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		sessionOne.beginTransaction();
		service.setPlanEndVolume(service.getPlanEndVolume() - 1);
		sessionOne.saveOrUpdate(service);
		sessionOne.getTransaction().commit();
	}

	@Override
	public List<CustomerService> getCustomersWithoutFreemiumByEndDate() {
		// TODO Auto-generated method stub
		List<CustomerService> renewCustomer = new ArrayList<CustomerService>();
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "from CustomerService cs where cs.active=true and cs.freemiumActive=false and cs.planEndDate<NOW()";
		Query query = session.createQuery(hql);
		List<CustomerService> list = query.getResultList();// int,double,double
		// prepare DeveloperShareOutput
		return list;
	}

	@Override
	public List<CustomerService> getCustomersWithFreemiumEnd() {
		// TODO Auto-generated method stub
		List<CustomerService> renewCustomer = new ArrayList<CustomerService>();
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = " from CustomerService css where css.id in (select cs.id from Freemium f , CustomerService cs where f.active =true and "
				+ "((f.type='D'and f.duration <NOW()) or " + "(f.type='V' and f.volume=0) or "
				+ "(f.type='B' and (f.duration<NOW() or f.volume=0))) and cs.customerId = f.consumerId and cs.serviceId =f.serviceId )";
		Query query = session.createQuery(hql);
		List<CustomerService> list = query.getResultList();// int,double,double
		// prepare DeveloperShareOutput
		return list;
	}
}

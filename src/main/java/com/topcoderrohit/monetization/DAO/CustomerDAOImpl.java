package com.topcoderrohit.monetization.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.topcoderrohit.monetization.model.Customer;
import com.topcoderrohit.monetization.model.CustomerService;
import com.topcoderrohit.monetization.model.Freemium;
import com.topcoderrohit.monetization.model.Transaction;
import com.topcoderrohit.monetization.util.HibernateUtil;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public int signUp(Customer customer) {
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		int id = (int) sessionOne.save(customer);
		sessionOne.getTransaction().commit();
		return id;
	}

	@Override
	public int saveFreemiumAccount(Freemium free) {
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		int id = (int) sessionOne.save(free);
		sessionOne.getTransaction().commit();
		return id;

	}

	@Override
	public int savePaidAccount(CustomerService service) {
		// TODO Auto-generated method stub
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		int id = (int) sessionOne.save(service);
		sessionOne.getTransaction().commit();
		return id;
	}

	@Override
	public void saveUpdatePaidAccount(CustomerService service) {
		// TODO Auto-generated method stub
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		sessionOne.saveOrUpdate(service);
		sessionOne.getTransaction().commit();
	}

	@Override
	public int saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub

		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		int id = (int) sessionOne.save(transaction);
		sessionOne.getTransaction().commit();
		return id;

	}

	@Override
	public void saveUpdateTransaction(Transaction transaction) {
		// TODO Auto-generated method stub

		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		sessionOne.saveOrUpdate(transaction);
		sessionOne.getTransaction().commit();

	}

	@Override
	public Transaction getTransaction(int developerServiceId) {
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		Criteria cr = sessionOne.createCriteria(Transaction.class);
		cr.add(Restrictions.eq("developerServiceId", String.valueOf(developerServiceId)));
		List results = cr.list();
		Transaction tran = new Transaction();
		if (results.size() > 0) {
			tran = (Transaction) results.get(0);
		}
		sessionOne.getTransaction().commit();

		return tran;

	}
	@Override
	public void cancelPlan(int customerId, int serviceId) {
		// TODO Auto-generated method stub
		Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();
		Criteria cr = sessionOne.createCriteria(CustomerService.class);
		cr.add(Restrictions.eq("serviceId", String.valueOf(serviceId)));
		cr.add(Restrictions.eq("customerId", String.valueOf(customerId)));
		List results = cr.list();
		CustomerService serviceDetail = (CustomerService) results.get(0);
		serviceDetail.setActive(false);
		sessionOne.update(serviceDetail);
		sessionOne.getTransaction().commit();
		
	}

}

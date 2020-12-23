package com.luv2code.springdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query ... sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", 
																Customer.class);
		
		// get the customer list from the query
		List<Customer> customers = theQuery.getResultList();
		
		// return list of customer
		return customers;
	}
	
	@Override
	public void saveCustomer(Customer newCustomer) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/update the customer
		currentSession.saveOrUpdate(newCustomer);
	}
	
	@Override
	public Customer getCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer customer = currentSession.get(Customer.class, theId);
		return customer;
	}
	
	@Override
	public void deleteCustomer(int theId) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary with Query
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId")
									   .setParameter("customerId",theId);
		theQuery.executeUpdate();
		
	}
	
	@Override
	public List<Customer> searchCustomer (String searchName) {
		Session currentSession = sessionFactory.getCurrentSession();
		List<Customer> customers;
		if (searchName != null && searchName.trim().length() > 0) {
			customers = currentSession.createQuery("from Customer where" +
													 " lower(firstName) LIKE :theName" +
													 " OR lower(lastName) LIKE :theName", Customer.class)
													.setParameter("theName", "%" + searchName.toLowerCase() + "%")
													.getResultList();
		}
		else {
			customers = currentSession.createQuery("from Customer",Customer.class).getResultList();
		}
		return customers;
	}
}

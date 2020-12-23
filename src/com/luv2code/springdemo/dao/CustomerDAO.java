package com.luv2code.springdemo.dao;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerDAO /* Data Access Object */ {
	
	public List<Customer> getCustomers();
	
	public void saveCustomer(Customer newCustomer);
	
	public Customer getCustomer(int theId);
	
	public void deleteCustomer(int theId);
	
	public List<Customer> searchCustomer(String searchName);
}

package com.wct.DAO;

import java.util.List;

import com.wct.entity.Customer;

public interface CustomerDAO {

	public Customer getCustomer(int id);
	public List<Customer> getCustomers();
	public void saveCustomer(Customer customer);
	public void deleteCustomer(int id);
		
}

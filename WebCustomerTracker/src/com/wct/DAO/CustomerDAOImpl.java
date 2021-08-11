package com.wct.DAO;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wct.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Customer getCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Customer customer = (Customer) session.get(Customer.class, id);
		
		return customer;
	}

	@Override
	public List<Customer> getCustomers() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = session.createQuery("from Customer Order by lastName", Customer.class);
		  List<Customer> customers = theQuery.getResultList();
		 
		/*
		 * CriteriaBuilder builder = session.getCriteriaBuilder();
		 * CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
		 * Root<Customer> root = query.from(Customer.class);
		 * query.select(root).orderBy(builder.asc(root.get("lastName")));
		 * Query<Customer> q = session.createQuery(query); List<Customer> customers =
		 * q.getResultList();
		 */
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		//current session
		Session session = sessionFactory.getCurrentSession();
		//save customer
		session.saveOrUpdate(customer);
	}

	@Override
	public void deleteCustomer(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query theQuery = session.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", id);
		
		theQuery.executeUpdate();
		
	}

	

}

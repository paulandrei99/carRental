package com.sda.paul.carrental.dao;


import com.sda.paul.carrental.model.Car;
import com.sda.paul.carrental.model.Customer;
import com.sda.paul.carrental.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDao {

    public Customer addCustomer(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(customer);

        transaction.commit();
        session.close();

        return customer;
    }

    public Customer getCustomerWithEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query<Customer> query = session.createQuery("from Customer where email = ?1");
        query.setParameter(1, email);
        Customer carsWithEngine = query.list().get(0);

        transaction.commit();
        session.close();

        return getCustomerWithEmail(email);
    }

}

package com.sda.paul.carrental.dao;

import com.sda.paul.carrental.model.Car;
import com.sda.paul.carrental.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CarDao {

    public Car getCar(int idCar){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        //READ
        Car car = session.get(Car.class, idCar);
        //INSERT / UPDATE
//        session.saveOrUpdate(car);
        //DELETE
//        session.delete(car);
        transaction.commit();
        session.close();

        return car;
    }

    //Testing git

    public List<Car> getCarsWithEngine(int engineSize){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query<Car> query = session.createQuery("from Car where engine = ?1");
        query.setParameter(1,engineSize);

        List<Car> carsWithEngine = query.list();
        transaction.commit();
        session.close();

        return carsWithEngine;
    }

}

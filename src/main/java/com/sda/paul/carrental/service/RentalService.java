package com.sda.paul.carrental.service;

import com.sda.paul.carrental.dao.CarDao;
import com.sda.paul.carrental.model.Car;

import java.util.List;

public class RentalService {

    private CarDao carDao = new CarDao();

    public Car getCar(int idCar){
        return carDao.getCar(idCar);
    }





}

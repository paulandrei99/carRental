package com.sda.paul.carrental.service;

import com.sda.paul.carrental.dao.CarDao;
import com.sda.paul.carrental.model.Car;

import java.util.List;

public class CarService {

    private CarDao carDao = new CarDao();

    public Car getCar(int idCar) {
        return carDao.getCar(idCar);
    }

    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }


}

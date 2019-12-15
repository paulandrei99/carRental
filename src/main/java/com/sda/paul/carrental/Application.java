package com.sda.paul.carrental;


import com.sda.paul.carrental.dao.CarDao;

public class Application {

    public static void main(String[] args) {

        CarDao carDao = new CarDao();
        System.out.println(carDao.getCarsWithEngine(2000));

//        System.out.println(carDao.getCar(1));


    }
}

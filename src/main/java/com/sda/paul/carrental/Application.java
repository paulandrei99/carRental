package com.sda.paul.carrental;


import com.sda.paul.carrental.dao.CarDao;
import com.sda.paul.carrental.model.Car;
import com.sda.paul.carrental.view.CarRentalMainView;

public class Application extends CarRentalMainView {

    public static void main(String[] args) {
//        Car car = new Car("BMW","1 series","automatic","diesel",4,2000,177,2006,"sedan",4,140);

        CarDao carDao = new CarDao();
//        System.out.println(carDao.getCarsWithEngine(2000));
//        carDao.addCar(car);

//        System.out.println(carDao.getCar(1));
        /*   /EDIT CAR
        Car car = carDao.getCar();
        car.setType("sedan");
        carDao.editCar(car);  */

//        carDao.deleteCar(7);
        CarRentalMainView.launch();

    }
}

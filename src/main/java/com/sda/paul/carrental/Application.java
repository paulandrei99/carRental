package com.sda.paul.carrental;


import com.sda.paul.carrental.dao.CarDao;
import com.sda.paul.carrental.dao.CustomerDao;
import com.sda.paul.carrental.model.Car;
import com.sda.paul.carrental.model.Customer;
import com.sda.paul.carrental.view.CarRentalMainView;

public class Application extends CarRentalMainView {

    public static void main(String[] args) {
//        Car car = new Car("BMW","1 series","automatic","diesel",4,2000,177,2006,"sedan",4,140,"avaible");

//        CarDao carDao = new CarDao();
//        System.out.println(carDao.getCarsWithEngine(2000));
//        carDao.addCar(car);

//        System.out.println(carDao.getCar(1));

          /* //EDIT CAR
        Car car = carDao.getCar(9);
        car.setMake("Volkswagen");
        car.setModel("Golf");
        carDao.editCar(car);
          */

//      carDao.deleteCar(7);

//        Customer customer = new Customer("Raul", "Vladimir", "rvd@yahoo.com", 0721, "Str.Primariei");
//        CustomerDao customerDao = new CustomerDao();
//        customerDao.addCustomer(customer);

        CarRentalMainView.launch();

    }
}
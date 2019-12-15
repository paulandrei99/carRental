package com.sda.paul.carrental.view;


import com.sda.paul.carrental.service.RentalService;
import com.sda.paul.carrental.util.HibernateUtil;
import javafx.application.Application;
import javafx.stage.Stage;

public class CarRentalMainView extends Application {

    RentalService rentalService = new RentalService();

    @Override
    public void start(Stage primaryStage) throws Exception{
        RentalService rentalService = new RentalService();
        System.out.println(rentalService.getCar(2));

    }





}

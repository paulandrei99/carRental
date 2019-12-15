package com.sda.paul.carrental.view;


import com.sda.paul.carrental.model.Car;
import com.sda.paul.carrental.service.CarService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CarRentalMainView extends Application {

    CarService carService = new CarService();

    @Override
    public void start(Stage primaryStage) throws Exception{
        CarService carService = new CarService();

        primaryStage.setTitle("Rent a car !");
        primaryStage.setHeight(400);
        primaryStage.setWidth(1100);
        primaryStage.setX(400);
        primaryStage.setY(300);

        TableView tableView = new TableView();

        TableColumn<String, Car> column1 = new TableColumn<>("car id");
        column1.setCellValueFactory(new PropertyValueFactory<>("idCar"));

        TableColumn<String, Car> column2 = new TableColumn<>("make");
        column2.setCellValueFactory(new PropertyValueFactory<>("make"));

        TableColumn<String, Car> column3 = new TableColumn<>("model");
        column3.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<String, Car> column4 = new TableColumn<>("gearbox");
        column4.setCellValueFactory(new PropertyValueFactory<>("gearbox"));

        TableColumn<String, Car> column5 = new TableColumn<>("fuel");
        column5.setCellValueFactory(new PropertyValueFactory<>("fuel"));

        TableColumn<String, Car> column6 = new TableColumn<>("doors");
        column6.setCellValueFactory(new PropertyValueFactory<>("doors"));

        TableColumn<String, Car> column7 = new TableColumn<>("engine");
        column7.setCellValueFactory(new PropertyValueFactory<>("engine"));

        TableColumn<String, Car> column8 = new TableColumn<>("power");
        column8.setCellValueFactory(new PropertyValueFactory<>("power"));

        TableColumn<String, Car> column9 = new TableColumn<>("year");
        column9.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<String, Car> column10 = new TableColumn<>("type");
        column10.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<String, Car> column11 = new TableColumn<>("seats");
        column11.setCellValueFactory(new PropertyValueFactory<>("seats"));

        TableColumn<String, Car> column12 = new TableColumn<>("price per day");
        column12.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.getColumns().add(column6);
        tableView.getColumns().add(column7);
        tableView.getColumns().add(column8);
        tableView.getColumns().add(column9);
        tableView.getColumns().add(column10);
        tableView.getColumns().add(column11);
        tableView.getColumns().add(column12);

        tableView.getItems().add(carService.getCar(1));
        tableView.getItems().add(carService.getCar(2));
        tableView.getItems().add(carService.getCar(3));



        Button button = new Button("Rent");
        HBox hBox = new HBox(button,tableView);
        hBox.setSpacing(50);
        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }





}

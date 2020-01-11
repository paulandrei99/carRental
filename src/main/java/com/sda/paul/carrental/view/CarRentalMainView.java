package com.sda.paul.carrental.view;


import com.sda.paul.carrental.dao.CarDao;
import com.sda.paul.carrental.dao.CustomerDao;
import com.sda.paul.carrental.model.Car;
import com.sda.paul.carrental.model.Customer;
import com.sda.paul.carrental.service.CarService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;

public class CarRentalMainView extends Application {
    Stage window;
    Scene returnCarScene;
    Scene newCustomerScene;
    Scene rentCarScene;
    CarService carService = new CarService();

    @Override
    public void start(Stage primaryStage) throws Exception {
        CarService carService = new CarService();
        window = primaryStage;

        primaryStage.setTitle("Rent a car !");
        primaryStage.setHeight(420);
        primaryStage.setWidth(1320);
        primaryStage.setX(0);
        primaryStage.setY(25);
        VBox vBox = new VBox();
        vBox.setSpacing(30);
        Scene firstScene = new Scene(vBox);
        primaryStage.setScene(firstScene);

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

        TableColumn<String, Car> column13 = new TableColumn<>("status");
        column13.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Car, String> column14 = new TableColumn<>("customer e-mail");
        column14.setCellValueFactory(cellData -> {
            if(cellData.getValue().getCustomer() != null) {
                return new SimpleStringProperty(cellData.getValue().getCustomer().getEmail());
            }else{
                return null;
            }
        });



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
        tableView.getColumns().add(column13);
        tableView.getColumns().add(column14);

        List<Car> cars = carService.getAllCars();

        for (int i = 0; i < cars.size(); i++) {
            try {
                System.out.println(cars.get(i).getCustomer().getEmail());
            } catch (Exception e) {
                System.out.println("Exception caught");
            }
            tableView.getItems().add(cars.get(i));
        }

        Button rentButton = new Button("Rent a car");
        rentButton.setPrefSize(170, 80);
        rentButton.setTextFill(Color.web("#0076a3"));
        rentButton.setFont(new Font(23));


        rentButton.setOnAction(event -> {
            window.setScene(rentCarScene);
//            for (int i = 1; i <= carService.getAllCars().size(); i++) {
//                tableView.getItems().add(carService.getCar(i));
//            }
        });
        Button backRentCarButton = new Button("Back");
        backRentCarButton.setOnAction(event -> {
            window.setScene(firstScene);
        });
        Label emailRentSceneLabel = new Label("Customer e-mail:");
        TextField emailRentSceneText = new TextField();
        Label carIdRentSceneLabel = new Label("Car id:");
        TextField carIdRentSceneText = new TextField();
        Button rentCarButton = new Button("Rent");
        rentCarButton.setMinWidth(70);
        rentCarButton.setMinHeight(50);
        rentCarButton.setOnAction(event -> {
            int idTxt = Integer.parseInt(carIdRentSceneText.getText());
            String emailRentScene = emailRentSceneText.getText();
            CarDao carDao = new CarDao();
            Car car = carDao.getCar(idTxt);
            if(car.getStatus().equals("available")){
//                car.setCustomer(emailRentScene);
            }

            tableView.getItems().clear();
            for (int i = 1; i <= carService.getAllCars().size(); i++) {
                tableView.getItems().add(carService.getCar(i));
            }
        });

        HBox rentCarHBox = new HBox();
        rentCarHBox.setSpacing(15);
        rentCarHBox.getChildren().addAll(emailRentSceneLabel, emailRentSceneText, carIdRentSceneLabel, carIdRentSceneText,
                rentCarButton);
        VBox rentCarVBox = new VBox();
        rentCarVBox.setSpacing(20);
        rentCarVBox.getChildren().addAll(backRentCarButton, rentCarHBox, tableView);
        rentCarScene = new Scene(rentCarVBox, 300, 300);

//        rentButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                int idTxt = Integer.parseInt(idTextField1Scene.getText());
//                CarDao carDao = new CarDao();
//                Car car = carDao.getCar(idTxt);
//                car.setStatus("rented");
//                carDao.editCar(car);
//                tableView.getItems().clear();
//                for (int i = 1; i <= carService.getAllCars().size(); i++) {
//                    tableView.getItems().add(carService.getCar(i));
//                }
//
//            }
//        });
//        rentButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);


//        Label idLabel = new Label("ID:");


        Button newCustomerButton = new Button("New customer");
        newCustomerButton.setPrefSize(170, 80);
        newCustomerButton.setFont(new Font(20));
        newCustomerButton.setOnAction(event -> {
            window.setScene(newCustomerScene);
        });

        Button backCustomerButton = new Button("Back");
        backCustomerButton.setOnAction(event -> window.setScene(firstScene));
        Label firstNameCustomerLabel = new Label("First Name:");
        TextField firstNameCustomerScene = new TextField();
        Label lastNameCustomerLabel = new Label("Last Name:");
        TextField lastNameCustomerScene = new TextField();
        Label emailCustomerLabel = new Label("Email:");
        TextField emailCustomerScene = new TextField();
        Label phoneCustomerLabel = new Label("Phone Number:");
        TextField phoneCustomerScene = new TextField();
        Label addressCustomerLabel = new Label("Address:");
        TextField addressCustomerScene = new TextField();
        Button createNewCustomerButton = new Button("Create new customer");
        createNewCustomerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                CustomerDao customerDao = new CustomerDao();
                Customer customer = new Customer(firstNameCustomerScene.getText(), lastNameCustomerScene.getText(),
                        emailCustomerScene.getText(), Integer.parseInt(phoneCustomerScene.getText()),
                        addressCustomerScene.getText());
                customerDao.addCustomer(customer);
                window.setScene(firstScene);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("You just created a new customer !");
                alert.showAndWait();
            }
        });

//        HBox customerHBox = new HBox();
//        customerHBox.getChildren().addAll(firstNameCustomerLabel, firstNameCustomerScene,
//                lastNameCustomerLabel, lastNameCustomerScene, emailCustomerLabel, emailCustomerScene, phoneCustomerLabel,
//                phoneCustomerScene, addressCustomerLabel, addressCustomerScene);
//        HBox customerButtonsHBox = new HBox();
//        customerButtonsHBox.getChildren().addAll(backCustomerButton, createNewCustomerButton);
//        customerButtonsHBox.setAlignment(Pos.CENTER);
//        customerButtonsHBox.setSpacing(100);
//        VBox customerVBox = new VBox();
//        customerVBox.getChildren().addAll(customerHBox, customerButtonsHBox);

        GridPane gridPane = new GridPane();
        gridPane.add(firstNameCustomerLabel, 0, 0);
        gridPane.add(firstNameCustomerScene, 1, 0);
        gridPane.add(lastNameCustomerLabel, 0, 1);
        gridPane.add(lastNameCustomerScene, 1, 1);
        gridPane.add(emailCustomerLabel, 0, 2);
        gridPane.add(emailCustomerScene, 1, 2);
        gridPane.add(phoneCustomerLabel, 0, 3);
        gridPane.add(phoneCustomerScene, 1, 3);
        gridPane.add(addressCustomerLabel, 0, 4);
        gridPane.add(addressCustomerScene, 1, 4);
        gridPane.add(createNewCustomerButton, 0, 6);
        gridPane.add(backCustomerButton, 0, 12);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        newCustomerScene = new Scene(gridPane, 300, 300);


        Button returnButton = new Button("Return car");
        returnButton.setPrefSize(170, 80);
        returnButton.setFont(new Font(23));
        returnButton.setOnAction(event -> {
            window.setScene(returnCarScene);
        });
        Button backReturnCarButton = new Button("Back");
        backReturnCarButton.setOnAction(event -> window.setScene(firstScene));
        Button returnCarButton = new Button("Click to return");
        Label idLabelReturnScene = new Label("Car ID:");
        TextField idTextFieldReturnScene = new TextField();

        returnCarButton.setOnAction(event -> {
            int idTxt = Integer.parseInt(idTextFieldReturnScene.getText());
            CarDao carDao = new CarDao();
            Car car = carDao.getCar(idTxt);
            car.setStatus("available");
            carDao.editCar(car);
            tableView.getItems().clear();
            for (int i = 1; i <= carService.getAllCars().size(); i++) {
                tableView.getItems().add(carService.getCar(i));
            }
            window.setScene(firstScene);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You just return the car with id " + idTxt);
            alert.showAndWait();
        });

//        HBox hBox = new HBox();
//        hBox.getChildren().addAll(backReturnCarButton, returnCarButton, idLabelReturnScene, idTextFieldReturnScene);
        GridPane gridPaneReturnScene = new GridPane();
        gridPaneReturnScene.add(idLabelReturnScene, 0, 0);
        gridPaneReturnScene.add(idTextFieldReturnScene, 0, 1);
        gridPaneReturnScene.add(returnCarButton, 1, 1);
        gridPaneReturnScene.add(backReturnCarButton, 0, 10);
        gridPaneReturnScene.setHgap(5);
        gridPaneReturnScene.setVgap(5);
        returnCarScene = new Scene(gridPaneReturnScene, 600, 300);


        vBox.getChildren().addAll(rentButton, newCustomerButton, returnButton);
        vBox.setAlignment(Pos.CENTER);
        primaryStage.show();


    }


}

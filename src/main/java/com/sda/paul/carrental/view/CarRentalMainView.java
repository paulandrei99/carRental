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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import sun.invoke.empty.Empty;

import java.util.List;
import java.util.Optional;

public class CarRentalMainView extends Application {
    Stage window;
    Scene returnCarScene;
    Scene newCustomerScene;
    Scene rentCarScene;
    Scene newCarScene;
    CarService carService = new CarService();
    CustomerDao customerDao = new CustomerDao();

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        primaryStage.setTitle("Rent a car !");
        primaryStage.setHeight(620);
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

        TableColumn<String, Car> column12 = new TableColumn<>("price per day ($)");
        column12.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));

        TableColumn<String, Car> column13 = new TableColumn<>("status");
        column13.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Car, String> column14 = new TableColumn<>("customer e-mail");
        column14.setCellValueFactory(cellData -> {
            if (cellData.getValue().getCustomer() != null) {
                return new SimpleStringProperty(cellData.getValue().getCustomer().getEmail());
            } else {
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
        });
        Button backRentCarButton = new Button("Back");
        backRentCarButton.setOnAction(event -> {
            window.setScene(firstScene);
        });
        backRentCarButton.setTextFill(Color.RED);
        Label emailRentSceneLabel = new Label("Customer e-mail:");
        TextField emailRentSceneText = new TextField();
        Label carIdRentSceneLabel = new Label("Car id:");
        TextField carIdRentSceneText = new TextField();
        Label daysRentSceneLabel = new Label("Number of days:");
        TextField daysRentSceneText = new TextField();
        Button rentCarButton = new Button("Rent");
        rentCarButton.setMinWidth(70);
        rentCarButton.setMinHeight(50);
        rentCarButton.setOnAction(event -> {

            int idTxt = Integer.parseInt(carIdRentSceneText.getText());
            String emailRentScene = emailRentSceneText.getText();
            CarDao carDao = new CarDao();
            Car car = carDao.getCar(idTxt);
            if (car.getStatus().equals("available")) {
                int x = 0;
                try {
                    car.setCustomer(customerDao.getCustomerWithEmail(emailRentScene));
                } catch (Exception e) {
                    System.out.println("The customer with introduced email is not registered");
                    x = 1;
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning !");
                    alert.setHeaderText("Warning");
                    alert.setContentText("The customer with introduced email is not registered");
                    alert.showAndWait();
                }
                if (x == 0) {
                    car.setStatus("rented");
                    //customerDao.getCustomerWithEmail(emailRentScene)
                    carDao.editCar(car);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    int price = car.getPricePerDay() * Integer.parseInt(daysRentSceneText.getText());
                    alert.setContentText("You just rented car with id " + idTxt + " for " + price + "$ !");
                    alert.showAndWait();
//                    tableView.getItems().clear();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning !");
                alert.setHeaderText("Warning");
                alert.setContentText("Car with id " + idTxt + " is already rented !");
                alert.showAndWait();
            }
            tableView.getItems().clear();
            for (int i = 1; i <= carService.getAllCars().size(); i++) {
                tableView.getItems().add(carService.getCar(i));
            }

        });

        HBox rentCarHBox = new HBox();
        rentCarHBox.setSpacing(15);
        rentCarHBox.getChildren().addAll(emailRentSceneLabel, emailRentSceneText, carIdRentSceneLabel, carIdRentSceneText,
                daysRentSceneLabel, daysRentSceneText, rentCarButton);
        VBox rentCarVBox = new VBox();
        rentCarVBox.setSpacing(20);
        rentCarVBox.getChildren().addAll(backRentCarButton, rentCarHBox, tableView);
        rentCarScene = new Scene(rentCarVBox, 300, 300);

        Button newCustomerButton = new Button("New customer");
        newCustomerButton.setPrefSize(170, 80);
        newCustomerButton.setFont(new Font(20));
        newCustomerButton.setTextFill(Color.web("#3ce312"));
        newCustomerButton.setOnAction(event -> {
            window.setScene(newCustomerScene);
        });

        Button backCustomerButton = new Button("Back");
        backCustomerButton.setOnAction(event -> window.setScene(firstScene));
        backCustomerButton.setTextFill(Color.RED);
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

        GridPane gridPaneCustomer = new GridPane();
        gridPaneCustomer.add(firstNameCustomerLabel, 0, 0);
        gridPaneCustomer.add(firstNameCustomerScene, 1, 0);
        gridPaneCustomer.add(lastNameCustomerLabel, 0, 1);
        gridPaneCustomer.add(lastNameCustomerScene, 1, 1);
        gridPaneCustomer.add(emailCustomerLabel, 0, 2);
        gridPaneCustomer.add(emailCustomerScene, 1, 2);
        gridPaneCustomer.add(phoneCustomerLabel, 0, 3);
        gridPaneCustomer.add(phoneCustomerScene, 1, 3);
        gridPaneCustomer.add(addressCustomerLabel, 0, 4);
        gridPaneCustomer.add(addressCustomerScene, 1, 4);
        gridPaneCustomer.add(createNewCustomerButton, 0, 6);
        gridPaneCustomer.add(backCustomerButton, 0, 12);
        gridPaneCustomer.setHgap(10);
        gridPaneCustomer.setVgap(10);
        newCustomerScene = new Scene(gridPaneCustomer, 300, 300);

        Button returnButton = new Button("Return car");
        returnButton.setPrefSize(170, 80);
        returnButton.setFont(new Font(23));
        returnButton.setTextFill(Color.web("#e3d912"));
        returnButton.setOnAction(event -> {
            window.setScene(returnCarScene);
        });
        Button backReturnCarButton = new Button("Back");
        backReturnCarButton.setOnAction(event -> window.setScene(firstScene));
        backReturnCarButton.setTextFill(Color.RED);
        Button returnCarButton = new Button("Click to return");
        Label idLabelReturnScene = new Label("Car ID:");
        TextField idTextFieldReturnScene = new TextField();

        returnCarButton.setOnAction(event -> {
            int idTxt = Integer.parseInt(idTextFieldReturnScene.getText());
            CarDao carDao = new CarDao();
            Car car = carDao.getCar(idTxt);
            car.setStatus("available");
            car.setCustomer(null);
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

        Button newCarButton = new Button("New Car");
        newCarButton.setPrefSize(170, 80);
        newCarButton.setFont(new Font(23));
        newCarButton.setTextFill(Color.web("#e312b9"));
        newCarButton.setOnAction(event -> window.setScene(newCarScene));
        Label makeLabelCarScene = new Label("make:");
        TextField makeTextCarScene = new TextField();
        Label modelLabelCarScene = new Label("model:");
        TextField modelTextCarScene = new TextField();
        Label gearboxLabelCarScene = new Label("gearbox:");
        TextField gearboxTextCarScene = new TextField();
        Label fuelLabelCarScene = new Label("fuel:");
        TextField fuelTextCarScene = new TextField();
        Label doorsLabelCarScene = new Label("doors:");
        TextField doorsTextCarScene = new TextField();
        Label engineLabelCarScene = new Label("engine:");
        TextField engineTextCarScene = new TextField();
        Label powerLabelCarScene = new Label("power:");
        TextField powerTextCarScene = new TextField();
        Label yearLabelCarScene = new Label("year:");
        TextField yearTextCarScene = new TextField();
        Label typeLabelCarScene = new Label("type:");
        TextField typeTextCarScene = new TextField();
        Label seatsLabelCarScene = new Label("seats:");
        TextField seatsTextCarScene = new TextField();
        Label priceLabelCarScene = new Label("price per day:");
        TextField priceTextCarScene = new TextField();

        Button backNewCarButton = new Button("Back");
        backNewCarButton.setOnAction(event -> window.setScene(firstScene));
        backNewCarButton.setTextFill(Color.RED);

        Button addNewCarButton = new Button("Add car");
        addNewCarButton.setPrefSize(60, 40);
        addNewCarButton.setOnAction(event -> {
            {

                CarDao carDao = new CarDao();
                Car car = new Car(makeTextCarScene.getText(), modelTextCarScene.getText(), gearboxTextCarScene.getText(),
                        fuelTextCarScene.getText(), Integer.parseInt(doorsTextCarScene.getText()),
                        Integer.parseInt(engineTextCarScene.getText()), Integer.parseInt(powerTextCarScene.getText()),
                        Integer.parseInt(yearTextCarScene.getText()), typeTextCarScene.getText(),
                        Integer.parseInt(seatsTextCarScene.getText()), Integer.parseInt(priceTextCarScene.getText()), "available");
                carDao.addCar(car);
                window.setScene(firstScene);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("You just added a new car !");
                alert.showAndWait();
            }
        });

        GridPane gridPaneNewCar = new GridPane();
        gridPaneNewCar.add(makeLabelCarScene, 0, 0);
        gridPaneNewCar.add(makeTextCarScene, 1, 0);
        gridPaneNewCar.add(modelLabelCarScene, 0, 1);
        gridPaneNewCar.add(modelTextCarScene, 1, 1);
        gridPaneNewCar.add(gearboxLabelCarScene, 0, 2);
        gridPaneNewCar.add(gearboxTextCarScene, 1, 2);
        gridPaneNewCar.add(fuelLabelCarScene, 0, 3);
        gridPaneNewCar.add(fuelTextCarScene, 1, 3);
        gridPaneNewCar.add(doorsLabelCarScene, 0, 4);
        gridPaneNewCar.add(doorsTextCarScene, 1, 4);
        gridPaneNewCar.add(engineLabelCarScene, 0, 5);
        gridPaneNewCar.add(engineTextCarScene, 1, 5);
        gridPaneNewCar.add(powerLabelCarScene, 0, 6);
        gridPaneNewCar.add(powerTextCarScene, 1, 6);
        gridPaneNewCar.add(yearLabelCarScene, 0, 7);
        gridPaneNewCar.add(yearTextCarScene, 1, 7);
        gridPaneNewCar.add(typeLabelCarScene, 0, 8);
        gridPaneNewCar.add(typeTextCarScene, 1, 8);
        gridPaneNewCar.add(seatsLabelCarScene, 0, 9);
        gridPaneNewCar.add(seatsTextCarScene, 1, 9);
        gridPaneNewCar.add(priceLabelCarScene, 0, 10);
        gridPaneNewCar.add(priceTextCarScene, 1, 10);
        gridPaneNewCar.add(addNewCarButton, 0, 12);
        gridPaneNewCar.add(backNewCarButton, 0, 22);
        gridPaneNewCar.setHgap(10);
        gridPaneNewCar.setVgap(10);

        newCarScene = new Scene(gridPaneNewCar, 600, 600);

        GridPane gridPaneReturnScene = new GridPane();
        gridPaneReturnScene.add(idLabelReturnScene, 0, 0);
        gridPaneReturnScene.add(idTextFieldReturnScene, 0, 1);
        gridPaneReturnScene.add(returnCarButton, 1, 1);
        gridPaneReturnScene.add(backReturnCarButton, 0, 10);
        gridPaneReturnScene.setHgap(5);
        gridPaneReturnScene.setVgap(5);
        returnCarScene = new Scene(gridPaneReturnScene, 600, 300);


        vBox.getChildren().addAll(rentButton, newCustomerButton, returnButton, newCarButton);
        vBox.setAlignment(Pos.CENTER);
        primaryStage.show();


    }


}

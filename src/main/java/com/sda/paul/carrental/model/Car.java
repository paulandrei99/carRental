package com.sda.paul.carrental.model;

import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCar;
    private String make;
    private String model;
    private String gearbox;
    private String fuel;
    private int doors;
    private int engine;
    private int power;
    private int year;
    private String type;
    private int seats;
    private int pricePerDay;
    private String status;
    @ManyToOne
    private Customer customer;

    public Car() {

    }

    public Car(String make, String model, String gearbox, String fuel, int doors, int engine, int power, int year, String type, int seats, int pricePerDay, String status) {

        this.make = make;
        this.model = model;
        this.gearbox = gearbox;
        this.fuel = fuel;
        this.doors = doors;
        this.engine = engine;
        this.power = power;
        this.year = year;
        this.type = type;
        this.seats = seats;
        this.pricePerDay = pricePerDay;
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Car{" +
                "idCar=" + idCar +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", gearbox='" + gearbox + '\'' +
                ", fuel='" + fuel + '\'' +
                ", doors=" + doors +
                ", engine=" + engine +
                ", power=" + power +
                ", year=" + year +
                ", type='" + type + '\'' +
                ", seats=" + seats +
                ", pricePerDay=" + pricePerDay +
                ", status='" + status + '\'' +
                '}';
    }
}

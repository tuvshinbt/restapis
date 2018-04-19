/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restjee.entity.mongo;

import bt.pre.restjee.entity.Car;
import org.bson.types.ObjectId;

/**
 *
 * @author tuvshuu
 */
public class CarDoc {

    private ObjectId id;
    private int carId;
    private String make;
    private String model;
    private int manufacturingYear;
    private int miles;
    private String color;

    public CarDoc() {
    }

    public CarDoc(Car car) {
        this.carId = car.getId();
        this.make = car.getMake();
        this.model = car.getModel();
        this.manufacturingYear = car.getYear();
        this.miles = car.getMiles();
        this.color = car.getColor() != null ? car.getColor().toString() : "";
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(int manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Car car() {
        Car car = new Car(make, model, manufacturingYear, miles, bt.pre.restjee.entity.Color.valueOf(color));
        car.setId(carId);
        return car;
    }

}

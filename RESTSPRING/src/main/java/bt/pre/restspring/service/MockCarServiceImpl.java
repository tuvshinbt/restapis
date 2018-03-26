/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restspring.service;

import bt.pre.restspring.entity.Car;
import bt.pre.restspring.entity.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author tuvshuu
 */
@Service
public class MockCarServiceImpl implements CarService {

    public void initBeanMock() {
        System.out.println("### initBeanMock() has been called. ###");
    }

    public void destroyBeanMock() {
        System.out.println("### destroyBeanMock() has been called. ###");
    }

    private String testValue;

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        System.out.println("### setTestValue(" + testValue + ")");
        this.testValue = testValue;
    }

    @Override
    public List<Car> getCars() {
        System.out.println("MOCK getCars() is called");
        return new ArrayList<>(Arrays.asList(new Car("Mock", "Mock", 0, 0, Color.RED), new Car("Mock", "Mock", 0, 0, Color.RED)));
    }

    @Override
    public Car getCar(Integer id) {
        System.out.println("MOCK getCar(" + id + ") is called");
        Car car = new Car("Mock", "Mock", 0, 0, Color.WHITE);
        return car;
    }

    @Override
    public Integer createCar(Car car) {
        System.out.println("MOCK createCar(" + car.toString() + ") is called");
        return 0;
    }

    @Override
    public Boolean updateCar(Integer id, Car car) {
        car.setId(1);
        System.out.println("MOCK updateCar(" + car.toString() + ") is called");
        return true;
    }

    @Override
    public Boolean deleteCar(Integer id) {
        System.out.println("MOCK deleteCar(" + id + ") is called");
        return true;
    }

}

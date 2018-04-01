/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.soap.jee.service;

import bt.pre.soap.jee.dao.Car;
import bt.pre.soap.jee.dao.Color;
import bt.pre.soap.jee.service.qualifier.MockCarQualifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author tuvshuu
 */
//@Stateless
@MockCarQualifier
//@Named("MockCarServiceImpl")
public class MockCarServiceImpl implements CarService {

    @Override
    public List<Car> getCars() {
        System.out.println("MOCK getCars() is called");
        return new ArrayList<>(Arrays.asList(new Car("Mock", "Mock", 0, 0, Color.RED), new Car("Mock", "Mock", 0, 0, Color.RED)));
    }

    @Override
    public Car getCar(Integer id) {
        System.out.println("MOCK getCar(" + id + ") is called");
        return new Car("Mock", "Mock", 0, 0, Color.RED);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.bt.rest.spring.boot.service;

import java.util.List;
import mn.bt.rest.spring.boot.entity.Car;

/**
 *
 * @author tuvshuu
 */
public interface CarService {

    List<Car> getCars();

    Car getCar(Integer id);

    Integer createCar(Car car);

    Boolean updateCar(Integer id, Car car);

    Boolean deleteCar(Integer id);

}

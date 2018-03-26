/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restjee.service;

import bt.pre.restjee.entity.Car;
import java.util.List;

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

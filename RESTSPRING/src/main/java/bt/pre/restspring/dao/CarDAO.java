/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restspring.dao;

import bt.pre.restspring.entity.Car;
import java.util.List;

/**
 *
 * @author tuvshuu
 */
public interface CarDAO {

    List<Car> findAll();

    Car findById(int id);

    Integer create(Car car);

    void update(int id, Car car);

    void delete(int id);

}

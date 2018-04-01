/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restjee.service;

import bt.pre.restjee.dao.CarDAO;
import bt.pre.restjee.entity.Car;
import bt.pre.restjee.service.qualifier.RealCarQualifier;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author tuvshuu
 */
//@Stateless
@RealCarQualifier
//@Named("RealCarServiceImpl")
public class RealCarServiceImpl implements CarService {
    
    @Inject
    private CarDAO carDAO;
    
    @Override
    public List<Car> getCars() {
        return carDAO.findAll();
    }
    
    @Override
    public Car getCar(Integer id) {
        return carDAO.findById(id);
    }
    
    @Override
    public Integer createCar(Car car) {
        return carDAO.create(car);
    }
    
    @Override
    public Boolean updateCar(Integer id, Car car) {
        carDAO.update(id, car);
        return true;
    }
    
    @Override
    public Boolean deleteCar(Integer id) {
        carDAO.delete(id);
        return true;
    }
    
}

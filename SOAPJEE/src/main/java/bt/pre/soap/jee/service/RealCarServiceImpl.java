/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.soap.jee.service;

import bt.pre.soap.jee.dao.Car;
import bt.pre.soap.jee.entity.CarDAO;
import bt.pre.soap.jee.service.qualifier.RealCarQualifier;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 *
 * @author tuvshuu
 */
//@Stateless
@RealCarQualifier
//@Named("RealCarServiceImpl")
public class RealCarServiceImpl implements CarService {
    
    @Inject
    @Default
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

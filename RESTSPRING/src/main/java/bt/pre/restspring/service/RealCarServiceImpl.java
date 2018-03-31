/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restspring.service;

import bt.pre.restspring.dao.CarDAO;
import bt.pre.restspring.entity.Car;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author tuvshuu
 */
@Service
public class RealCarServiceImpl implements CarService {

    public void initBeanReal() {
        System.out.println("### initBeanReal() has been called. ###");
    }

    public void destroyBeanReal() {
        System.out.println("### destroyBeanReal() has been called. ###");
    }

    @Autowired
//    @Qualifier("JdbcCarDAOImpl")
    private CarDAO carDAO;

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public void setCarDAO(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public List<Car> getCars() {
        System.out.println("*** REAL DB JDBC *** getCars() is called");
        return carDAO.findAll();
    }

    @Override
    public Car getCar(Integer id) {
        System.out.println("*** REAL DB JDBC *** getCar(" + id + ") is called");
        return carDAO.findById(id);
    }

    @Override
    public Integer createCar(Car car) {
        System.out.println("*** REAL DB JDBC *** createCar(" + car.toString() + ") is called");
        return carDAO.create(car);
    }

    @Override
    public Boolean updateCar(Integer id, Car car) {
        System.out.println("*** REAL DB JDBC *** updateCar(" + car.toString() + ") is called");
        carDAO.update(id, car);
        return true;
    }

    @Override
    public Boolean deleteCar(Integer id) {
        System.out.println("*** REAL DB JDBC *** deleteCar(" + id + ") is called");
        carDAO.delete(id);
        return true;
    }

}

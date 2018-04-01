/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.soap.jee.web.service;

import bt.pre.soap.jee.dao.Car;
import bt.pre.soap.jee.service.CarService;
import bt.pre.soap.jee.service.qualifier.RealCarQualifier;
import java.util.List;
import javax.jws.WebService;
import javax.inject.Inject;

/**
 *
 * @author tuvshuu
 */
@WebService(
        serviceName = "CarWebServiceImpl",
        endpointInterface = "bt.pre.soap.jee.web.service.CarWebService"
)
public class CarWebServiceImpl
        implements CarWebService {
    
    @Inject
    @RealCarQualifier
    private CarService carService;

    @Override
    public String hello(String name) {
        System.out.println("CarWebServiceImpl.hello() has been called.");
        return "Hello " + name;
    }

    @Override
    public List<Car> getCars() {
        return carService.getCars();
    }

    @Override
    public Car getCar(Integer id) {
        return carService.getCar(id);
    }

    @Override
    public Integer createCar(Car car) {
        return carService.createCar(car);
    }

    @Override
    public Boolean updateCar(Integer id, Car car) {
        return carService.updateCar(id, car);
    }

    @Override
    public Boolean deleteCar(Integer id) {
        return carService.deleteCar(id);
    }

}

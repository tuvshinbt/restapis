/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restjee.service;

import bt.pre.restjee.entity.Car;
import bt.pre.restjee.service.qualifier.RealCarQualifier;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tuvshuu
 */
@Stateless
//@RealCarQualifier
@Named("RealCarServiceImpl")
public class RealCarServiceImpl implements CarService {

    @PersistenceContext(unitName = "PU_RESTJEE")
    EntityManager em;

    @Override
    public List<Car> getCars() {
        List list = em.createQuery("SELECT c FROM Car c").getResultList();
        if (list != null) {
            return (List<Car>) list;
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Car getCar(Integer id) {
        return em.find(Car.class, id);
    }

    @Override
    public Integer createCar(Car car) {
        em.persist(car);
        em.flush();
        return car.getId();
    }

    @Override
    public Boolean updateCar(Integer id, Car car) {
        Car oldCar = em.find(Car.class, id);
        if (oldCar != null) {
            oldCar.setMake(car.getMake());
            oldCar.setModel(car.getModel());
            oldCar.setMiles(car.getMiles());
            oldCar.setYear(car.getYear());
            em.persist(oldCar);
        }
        return true;
    }

    @Override
    public Boolean deleteCar(Integer id) {
        Car oldCar = em.find(Car.class, id);
        if (oldCar != null) {
            em.remove(oldCar);
        }
        return true;
    }

}

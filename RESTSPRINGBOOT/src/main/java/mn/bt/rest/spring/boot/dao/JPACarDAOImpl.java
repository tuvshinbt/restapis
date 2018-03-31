/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.bt.rest.spring.boot.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mn.bt.rest.spring.boot.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tuvshuu
 */
@Repository
public class JPACarDAOImpl implements CarDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Car> findAll() {
        List list = em.createQuery("SELECT c FROM Car c").getResultList();
        if (list != null) {
            return (List<Car>) list;
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Car findById(int id) {
        return em.find(Car.class, id);
    }

    @Override
    public Integer create(Car car) {
        em.persist(car);
        em.flush();
        return car.getId();
    }

    @Override
    public void update(int id, Car car) {
        Car oldCar = em.find(Car.class, id);
        if (oldCar != null) {
            oldCar.setMake(car.getMake());
            oldCar.setModel(car.getModel());
            oldCar.setMiles(car.getMiles());
            oldCar.setYear(car.getYear());
            em.persist(oldCar);
        }
    }

    @Override
    public void delete(int id) {
        Car oldCar = em.find(Car.class, id);
        if (oldCar != null) {
            em.remove(oldCar);
        }
    }

}

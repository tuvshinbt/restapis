/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restspring.dao;

import bt.pre.restspring.entity.Car;
import java.awt.print.Book;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tuvshuu
 */
@Repository
@Transactional
public class HibernateCarDAOImpl implements CarDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Car> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Car> cars = session.createQuery("Select c from Car c").list();
        return cars;

//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
//        Root<Car> root = cq.from(Car.class);
//        cq.select(root);
//        Query<Car> query = session.createQuery(cq);
//        return query.getResultList();
    }

    @Override
    public Car findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Car car = (Car) session.find(Car.class, new Integer(id));
        return car;
    }

    @Override
    public Integer create(Car car) {
        Session session = this.sessionFactory.getCurrentSession();
//        session.save(car);
        session.persist(car);
//        session.flush();
        return car.getId();
    }

    @Override
    public void update(int id, Car car) {
        Session session = sessionFactory.getCurrentSession();
        Car oldCar = session.byId(Car.class).load(id);
        if (oldCar != null) {
            oldCar.setMake(car.getMake());
            oldCar.setModel(car.getModel());
            oldCar.setMiles(car.getMiles());
            oldCar.setYear(car.getYear());
            session.flush();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Car car = session.byId(Car.class).load(id);
        session.delete(car);
    }

}

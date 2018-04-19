/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restjee.dao;

import bt.pre.restjee.entity.mongo.CarDoc;
import bt.pre.restjee.dao.provider.MongoClientProvider;
import bt.pre.restjee.dao.qualifier.MongoDBCarDAOQualifier;
import bt.pre.restjee.entity.Car;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author tuvshuu
 */
@Stateless
@MongoDBCarDAOQualifier
public class MongoDBCarDAO implements CarDAO {

    private String collectionName;

    public MongoDBCarDAO() {
        collectionName = "carCollection";
        System.out.println("\nMongoDBCarDAO is called\n");
    }

    @EJB
    MongoClientProvider mongoClientProvider;

    public MongoDatabase getDatabase() {
        return mongoClientProvider.getMongoClient().getDatabase(mongoClientProvider.getDataName());
    }

    public MongoCollection<CarDoc> getCollection() {
        return getDatabase().getCollection(collectionName, CarDoc.class);
    }

    @Override
    public List<Car> findAll() {
        MongoCollection<CarDoc> carCol = getCollection();
        FindIterable<CarDoc> docList = carCol.find();
        MongoCursor<CarDoc> cursor = docList.iterator();
        List<Car> carList = new ArrayList<>();
        while (cursor.hasNext()) {
            Car car = cursor.next().car();
            carList.add(car);
            System.out.println(car.toString());
        }
        return carList;
    }

    @Override
    public Car findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer create(Car car) {
        CarDoc carDoc = new CarDoc(car);
        getCollection().insertOne(carDoc);
        return carDoc.getCarId();
    }

    @Override
    public void update(int id, Car car) {
        getCollection().updateOne(Filters.eq("carId", id), Updates.combine(
                Updates.set("make", car.getMake()),
                Updates.set("model", car.getModel()),
                Updates.set("manufacturingYear", car.getYear()),
                Updates.set("miles", car.getMiles()),
                Updates.set("color", car.getColor().value())
        ));
    }

    @Override
    public void delete(int id) {
        getCollection().deleteOne(Filters.eq("carId", id));
    }

}

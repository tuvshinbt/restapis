/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restspring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tuvshuu
 */
@XmlRootElement(name = "Cars")
public class Cars implements Serializable {

    List<Car> cars;

    public Cars() {
    }

    public Cars(List<Car> c) {
        cars = c;
    }

    @XmlElement(name = "Car")
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        cars.addAll(cars);
    }

}

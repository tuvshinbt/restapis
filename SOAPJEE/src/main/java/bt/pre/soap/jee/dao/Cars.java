/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.soap.jee.dao;

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
public class Cars extends ArrayList<Car> {

    public Cars() {
        super();
    }

    public Cars(Collection<? extends Car> c) {
        super(c);
    }

    @XmlElement(name = "Car")
    public List<Car> getCars() {
        return this;
    }

    public void setCars(List<Car> cars) {
        this.addAll(cars);
    }

}

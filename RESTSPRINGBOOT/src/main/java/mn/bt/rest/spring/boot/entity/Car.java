/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.bt.rest.spring.boot.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tuvshuu
 */
@Entity
@Table(name = "CarTbl")
@XmlRootElement(name = "Car")
public class Car implements Serializable {

    private static final long serialVersionUID = -7788619177798333712L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String Make;
    private String Model;
    @Column(name = "ManufacturingYear")
    private int Year;
    private int Miles;
    @Enumerated(EnumType.STRING)
    private Color color;

    public Car() {
    }

    public Car(String Make, String Model, int Year, int Miles, Color color) {
        this.Make = Make;
        this.Model = Model;
        this.Year = Year;
        this.Miles = Miles;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return Make;
    }

    @XmlElement
    public void setMake(String Make) {
        this.Make = Make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public int getMiles() {
        return Miles;
    }

    public void setMiles(int Miles) {
        this.Miles = Miles;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ea.demo.lab1.entity.Car[ id=" + id + ", Make=" + Make + ", Model=" + Model + " ]";
    }

}

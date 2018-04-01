/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.soap.jee.client;

import bt.pre.soap.jee.web.service.Car;
import bt.pre.soap.jee.web.service.CarWebService;
import bt.pre.soap.jee.web.service.CarWebServiceImpl;
import java.util.List;

/**
 *
 * @author tuvshuu
 */
public class SOAPClient {

    private static CarWebServiceImpl carWebServiceImpl = new CarWebServiceImpl();

    public static void main(String[] args) {
        try {
            CarWebService carWebService = carWebServiceImpl.getCarWebServiceImplPort();
            System.out.println("Greeting (function.Hello() : " + carWebService.hello("Tuvshuu"));
            List<Car> carList = carWebService.getCars();
            System.out.println("getCars - " + carList.toString());
            Car car = carWebService.getCar(2);
            System.out.println("getCar(2) - " + car.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

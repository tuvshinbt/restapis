/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.bt.rest.spring.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import mn.bt.rest.spring.boot.entity.Car;
import mn.bt.rest.spring.boot.entity.Cars;
import mn.bt.rest.spring.boot.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author tuvshuu
 */
@RestController
@RequestMapping(value = "/restapi/car")
public class CarRestController {

    public CarRestController() {
        System.out.printf("### %s() has been CREATED ###\n", "CarRestController");
    }

    @Autowired
    @Qualifier("realCarServiceImpl")
    private CarService carService;

    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "")
    public ResponseEntity<Cars> getCars(WebRequest webRequest) {
        System.out.printf("### CarRestController %s() has been called ###\n", "getCars");

        List<Car> carList = carService.getCars();
        Cars cars = new Cars(carList);
        String etag = carList.hashCode() + "";
        System.out.println("etag - " + etag);
        // To check header If-None-Match value
        if (webRequest.checkNotModified(etag)) {
            System.out.println("1. Content hasn't been changed");
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        if (!carList.isEmpty()) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(5, TimeUnit.SECONDS))
                    .eTag(etag)
                    .body(cars);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    // with extension (.xml or .json)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Car> getCar(@RequestHeader(name = "Content-Type", defaultValue = "application/xml") String contentType,
            @PathVariable Integer id) {
        System.out.println("contentType - " + contentType);
        System.out.printf("### CarRestController %s() has been called ###\n", "getCar(" + id + ")");
        Car car = carService.getCar(id);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<String> createCar(@RequestHeader(name = "Content-Type", defaultValue = "application/xml") String contentType,
            @RequestBody Car car,
            HttpServletRequest request) {

        System.out.printf("### CarRestController %s() has been called ###\n", "createCar(" + car.toString() + ")");
        System.out.println("contentType - " + contentType);
        System.out.println("URL - " + request.getRequestURL().toString());

        Integer carId = carService.createCar(car);
        Map<String, Integer> paramAndValue = new HashMap<>();
        paramAndValue.put("", carId);
        return new ResponseEntity(request.getRequestURL().toString() + "/" + carId, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateCar(@RequestHeader(name = "Content-Type", defaultValue = "application/xml") String contentType,
            @PathVariable Integer id,
            @RequestBody Car car) {

        System.out.printf("### CarRestController %s() has been called ###\n", "updateCar(" + id + "," + car.toString() + ")");
        System.out.println("contentType - " + contentType);

        if (carService.updateCar(id, car)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id) {

        System.out.printf("### CarRestController %s() has been called ###\n", "deleteCar(" + id + ")");

        if (carService.deleteCar(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}

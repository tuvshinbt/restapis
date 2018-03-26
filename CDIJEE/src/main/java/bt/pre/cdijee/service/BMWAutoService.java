/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.cdijee.service;

import bt.pre.cdijee.service.inf.AutoService;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

/**
 *
 * @author tuvshuu
 */
@Named("BMWAutoService")
public class BMWAutoService implements AutoService {

    @PostConstruct
    public void initMethod() {
        System.out.println("BMWAutoService PostConstruct() is called.");
    }

    public BMWAutoService() {
        System.out.println("BMWAutoService Bean is created");
    }

    @PreDestroy
    public void preDestroyMethod() {
        System.out.println("BMWAutoService preDestroyMethod() is called.");
    }

    @Override
    public String getService() {
        return "You chose BMW auto service";
    }
}

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
@Named("AudioAutoService")
public class AudioAutoService implements AutoService {

    @PostConstruct
    public void initMethod() {
        System.out.println("AudioAutoService PostConstruct() is called.");
    }

    public AudioAutoService() {
        System.out.println("AudioAutoService Bean is created");
    }

    @PreDestroy
    public void preDestroyMethod() {
        System.out.println("AudioAutoService preDestroyMethod() is called.");
    }

    @Override
    public String getService() {
        return "You chose Audio auto service";
    }
}

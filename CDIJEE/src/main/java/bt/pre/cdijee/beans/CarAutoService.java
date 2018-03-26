/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.cdijee.beans;

import bt.pre.cdijee.service.inf.AutoService;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;

/**
 *
 * @author tuvshuu
 */
@RequestScoped
@Named(value = "carAutoService")
public class CarAutoService {

    @Inject
    @Named("AudioAutoService")
    private AutoService autoService;

    public CarAutoService() {
        System.out.println("*** CarAutoService is created ***");
    }

    public String getServiceName() {
        return autoService.getService();
    }

}

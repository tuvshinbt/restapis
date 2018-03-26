/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restspring.core;

import bt.pre.restspring.controller.CarRestController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tuvshuu
 */
public class CoreSpring {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
        CarRestController carRestController = (CarRestController) context.getBean("carRestController");
    }
}

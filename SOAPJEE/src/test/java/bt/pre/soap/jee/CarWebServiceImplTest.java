/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.soap.jee;

import bt.pre.soap.jee.web.service.CarWebServiceImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tuvshuu
 */
public class CarWebServiceImplTest {

    CarWebServiceImpl carWebServiceImpl = lookupCarWebServiceImplBean();

    public CarWebServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//    @Test
    public void hello() {
        Assert.assertEquals("Hello Tuvshuu", carWebServiceImpl.hello("Tuvshuu"));
    }

    private CarWebServiceImpl lookupCarWebServiceImplBean() {
        try {
            Context c = new InitialContext();
            return (CarWebServiceImpl) c.lookup("java:global/SOAPJEE/CarWebServiceImpl!bt.pre.soap.jee.web.service.CarWebService");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}

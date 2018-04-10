/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pre.rest;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author tuvshuu
 */
public class CarRestTest {

    private static URI uri;
    private static Client client;

    public CarRestTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        uri = UriBuilder.fromUri("http://localhost:8080/RESTJEE/restapi/car").port(8080).build();
        client = ClientBuilder.newClient();
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
//    public void checkBadUrl() {
//        System.out.println("### checkBadUrl() CarRestTest.getCars() using <<< UriBuilder >>>");
//        Response response = client.target(uri).request().post(Entity.entity(null, MediaType.APPLICATION_XML));
//        Assert.assertEquals(Response.Status.BAD_REQUEST, response.getStatus());
//    }

//    @Test
//    public void getCarsTest() {
//        System.out.println("### getCarsTest() CarRestTest.getCars() using <<< EJBContainer >>>");
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(EJBContainer.MODULES, new File("Target/classes"));
//        try (EJBContainer ec = EJBContainer.createEJBContainer(properties)) {
//            Context ctx = ec.getContext();
//            Assert.assertNull(ctx.lookup("java:global/JNDI_TESTDB"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

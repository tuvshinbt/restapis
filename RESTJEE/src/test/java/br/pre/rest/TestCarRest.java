/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pre.rest;

import java.net.URI;
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
public class TestCarRest {

    private static URI uri = UriBuilder.fromUri("http://localhost:8080/RESTJEE/restapi/car").port(8080).build();
    private static Client client = ClientBuilder.newClient();

    public TestCarRest() {
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
//    public void nullCar() {
//        // POST a null car
//        Response response = client.target(uri).request().post(Entity.entity(null, MediaType.APPLICATION_XML));
//        Assert.assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo());
//    }
}

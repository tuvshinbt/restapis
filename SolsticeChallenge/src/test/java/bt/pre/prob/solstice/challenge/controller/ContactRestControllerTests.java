/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.controller;

import javax.ws.rs.core.MediaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author tuvshuu
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class ContactRestControllerTests {
    
    private static final String AUTH = "Authorization";
    private static final String BASIC = "Basic dXNlcjpwYXNz";
    
    @Autowired
    private MockMvc mvc;
    
    @Test
    public void testUnauthorizated() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/restapi/contact/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(401))
                .andExpect(content().string(""));
    }
    
    @Test
    public void testWrongContentType() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/restapi/contact/1")
                .accept(MediaType.APPLICATION_XML)
                .header(AUTH, BASIC))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("Oops! ERROR Could not find acceptable representation"));
    }
    
    @Test
    public void testNotfound() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/restapi/contact/0")
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTH, BASIC))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void tesFound() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/restapi/contact/1")
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTH, BASIC))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n"
                                + "    \"fullName\": \"Battuvshin Badarch\",\n"
                                + "    \"birthDate\": \"2018-04-27\",\n"
                                + "    \"profileImage\": \"imagesBattuvshin_Badarch.jpg\",\n"
                                + "    \"contactId\": 1,\n"
                                + "    \"email\": \"battuvshin.badarch@gmail.com\",\n"
                                + "    \"phoneNumber\": 3128419455,\n"
                                + "    \"address1\": \"4915 West Belle Plaine Avenue\",\n"
                                + "    \"address2\": null,\n"
                                + "    \"city\": \"Chicago\",\n"
                                + "    \"state\": \"IL\",\n"
                                + "    \"zipCode\": 60641,\n"
                                + "    \"companyName\": \"Solstice\",\n"
                                + "    \"companyPhone\": 987654322\n"
                                + "}"));
    }
}

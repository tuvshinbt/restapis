/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.bt.rest.spring.boot;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tuvshuu
 */
@RestController
@SpringBootApplication
public class RestSpringBootMain {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RestSpringBootMain.class, args);
    }

    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        System.out.printf("<<< %s >>>\n", "Shutdown");
        return () -> 42;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.bean;

import bt.pre.prob.solstice.challenge.entity.Address;
import bt.pre.prob.solstice.challenge.entity.Company;
import bt.pre.prob.solstice.challenge.entity.Contact;
import bt.pre.prob.solstice.challenge.entity.Person;
import bt.pre.prob.solstice.challenge.repository.PersonRepository;
import bt.pre.prob.solstice.challenge.validation.ContactModelValidator;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author tuvshuu
 */
@Component
public class InitBean implements ApplicationRunner {

    @Value("${name}")
    private String author;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.printf("<<< Author - %s >>>\n", author);
//        insertData();
    }

    @Autowired
    private PersonRepository personRepository;

    public void insertData() {
        Person person1 = new Person(author, new Date(), null, null, null);
        personRepository.save(person1);

        Person person2 = new Person(author, new Date(),
                new Contact(author, "battuvshin.badarch@gmail.com", 3218419455L),
                new Address("4915 West Belle Plaine", "", "Chicago", "IL", 60641),
                new Company("Solstice", 123456789L));
        personRepository.save(person2);
    }

    @Bean
    public ContactModelValidator beforeCreateContactModelValidator() {
        return new ContactModelValidator();
    }
}

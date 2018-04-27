/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.repository;

import bt.pre.prob.solstice.challenge.entity.Address;
import bt.pre.prob.solstice.challenge.entity.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tuvshuu
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByAddressIn(List<Address> addresses);
}

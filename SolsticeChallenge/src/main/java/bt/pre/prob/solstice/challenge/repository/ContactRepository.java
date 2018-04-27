/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.repository;

import bt.pre.prob.solstice.challenge.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tuvshuu
 */
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Contact findByEmail(String email);

    Contact findByPhoneNumber(Long phoneNumber);
}

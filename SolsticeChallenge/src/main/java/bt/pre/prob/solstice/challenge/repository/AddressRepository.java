/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.repository;

import bt.pre.prob.solstice.challenge.entity.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tuvshuu
 */
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByState(String state);

    List<Address> findByStateAndCity(String state, String city);
}

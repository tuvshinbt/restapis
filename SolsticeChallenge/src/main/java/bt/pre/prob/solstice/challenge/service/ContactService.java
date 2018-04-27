/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.service;

import bt.pre.prob.solstice.challenge.model.ContactModel;
import java.util.List;

/**
 *
 * @author tuvshuu
 */
public interface ContactService {

    Integer create(ContactModel contactModel);

    ContactModel retrieve(Integer id);

    void update(Integer id, ContactModel contactModel);

    void delete(Integer id);

    ContactModel search(ContactCriteria contactCriteria, String value);

    List<ContactModel> retrieveAll(String state, String city);

}
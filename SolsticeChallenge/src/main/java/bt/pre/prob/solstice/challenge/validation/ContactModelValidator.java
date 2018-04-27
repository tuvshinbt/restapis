/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.validation;

import bt.pre.prob.solstice.challenge.model.ContactModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author tuvshuu
 */
@Component("contactModelValidator")
public class ContactModelValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return ContactModel.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "fullName", "fullName.empty");
    }

}

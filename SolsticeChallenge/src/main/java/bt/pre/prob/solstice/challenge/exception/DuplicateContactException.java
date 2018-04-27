/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author tuvshuu
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DuplicateContactException extends RuntimeException {

    public DuplicateContactException(String exception) {
        super(exception);
    }
}

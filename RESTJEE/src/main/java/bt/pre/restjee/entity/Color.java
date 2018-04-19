/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restjee.entity;

/**
 *
 * @author tuvshuu
 */
public enum Color {

    RED("RED"), BLACK("BLACK"), WHITE("WHITE");
    private String value;

    Color(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}

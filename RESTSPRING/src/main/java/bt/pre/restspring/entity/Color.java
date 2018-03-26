/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.restspring.entity;

/**
 *
 * @author tuvshuu
 */
public enum Color {

    RED("red"), BLACK("black"), WHITE("white");

    private String value;

    Color(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

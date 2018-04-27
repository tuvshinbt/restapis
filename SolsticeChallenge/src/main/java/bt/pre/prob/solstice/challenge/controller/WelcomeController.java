/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author tuvshuu
 */
@Controller
public class WelcomeController {

    @RequestMapping(value = "/")
    public String welcome(Model model) {
        model.addAttribute("img", "");
        return "home";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.disney.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Rodrigo Caro
 */

@Controller
@RequestMapping("/auth/login")
public class LoginControlador {
    
   @GetMapping("")
    public String login(Model model, @RequestParam(required = false) String error, 
                                     @RequestParam(required = false) String username,
                                     @RequestParam(required = false) String logout){
        if (error != null) {
            model.addAttribute("error", "El usuario ingresado o la contraseña es ingresada es incorrecta");
        }
        if (username != null) {
            model.addAttribute("username", username);
        }
        return "login";
    }
}